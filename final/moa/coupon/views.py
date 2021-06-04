# from django.shortcuts import render

# # Create your views here.
from django.contrib import messages
from django.shortcuts import render, redirect
from django.http import HttpResponse, JsonResponse 
from django.views.decorators.csrf import csrf_exempt 
from .models import Coupon
from account.models import Customer
from .serializers import CouponSerializer
from rest_framework.parsers import JSONParser
from fuser.models import Fuser
from management.models import Manager
from django.core.exceptions import ObjectDoesNotExist

@csrf_exempt
def coupon(request) : # in mobile app
    
    #특정 쿠폰 조회
    if request.method == "GET" : #input : (customer, store), output : (store_id, current_cnt)
        customer = request.GET.get('customer',None)
        store = request.GET.get('store',None)
        try :
            customer_obj = Customer.objects.get(user_id = customer)
        except : 
            return HttpResponse(status=401)
            #customer 잘못됨
        try : 
            store_obj = Manager.objects.get(cafe_name = store)
        except : 
            return HttpResponse(status=402)
            #store 잘못됨
        try:
            coupon_obj = Coupon.objects.get(customer = customer_obj, store = store_obj)
        except :
            return HttpResponse(status=403)
            #user_id, store_name 조합 잘못됨

        serializer = CouponSerializer(coupon_obj)

        return JsonResponse(serializer.data, status=200)

#스탬프 선물하기 메소드 
#input : (string send_id, string get_id, string store_name, int stamp_num)
# #output : x
@csrf_exempt
def present(request, send_id, get_id) :
    try :
        send_obj = Customer.objects.get(user_id = send_id)
    except :
        return HttpResponse(status = 400) #보내는 유저 id 잘못됨
    try : 
        get_obj = Customer.objects.get(user_id = get_id)
    except :
        return HttpResponse(status = 401) #받는 유저 id 잘못됨           
           
    if request.method == "PUT" :
        data = JSONParser().parse(request)

        try : 
            store_obj = Manager.objects.get(cafe_name = data["cafe_name"])
        except :
            return HttpResponse(status = 402) #카페 이름 잘못됨
           
        stamp_num = int(data['stamp_num']) #선물할 스탬프 개수

        try:
            sender_coupon_obj = Coupon.objects.get(customer = send_obj, store = store_obj)
        except :
            return HttpResponse(status = 403) #쿠폰 없음(보내는 사람 등록되지 않은 카페)

        if sender_coupon_obj.current_cnt >= stamp_num :
            if Coupon.objects.filter(customer=get_obj, store = store_obj).exists() :            
                sender_coupon_obj.current_cnt -= stamp_num
                getter_coupon_obj = Coupon.objects.get(customer=get_obj, store = store_obj)
                getter_coupon_obj.current_cnt += stamp_num

                sender_coupon_obj.save()
                getter_coupon_obj.save()   
            else :
                sender_coupon_obj.current_cnt -= stamp_num
                getter_coupon_obj = Coupon(customer=get_obj, store = store_obj, current_cnt = stamp_num)

                sender_coupon_obj.save()
                getter_coupon_obj.save() 

            return HttpResponse(status=200)
        else :
            return HttpResponse(status = 404) #보내는 사람 스탬프 개수 부족

#아이디 받아서 쿠폰 리스트 출력하는 메소드
#input : ('customer') output : (customer , store list)
def coupon_list(request) :  
    if request.method == "GET" :
        customer = request.GET.get('customer',None)
        try :
            customer_obj = Customer.objects.get(user_id = customer)
        except : 
            return HttpResponse(status=401)
            #customer 잘못됨

        obj = Coupon.objects.filter(customer = customer_obj)
        serializer = CouponSerializer(obj, many = True)

        return JsonResponse(serializer.data,safe = False)



# on computer
def aboutcoupon(request):
    customers = Coupon.objects.all()
    context= {'customers':customers}
    
    session_id = request.session.session_key
    user = request.session['user']
    find_cafe = Fuser.objects.get(user_id=user).cafe_name

    if Manager.objects.filter(cafe_name=find_cafe).exists():
        cafe = Manager.objects.get(cafe_name=find_cafe)
    else:
        pass

    if request.method == "POST":    
        find_user_id = request.POST.get('userid', None)
        find_user_phone = request.POST.get('userphone', None)
            
        customer = Customer.objects.get(user_id=find_user_id)
    
        if request.POST.get('look_coupon'):
            try:
                if Coupon.objects.filter(customer = customer, store = cafe).exists():

                    cnt = Coupon.objects.get(customer=customer, store=cafe).current_cnt
                    messages.add_message(request, messages.INFO,'쿠폰 개수 %d'%(cnt))
                    return render(request, 'aboutcoupon.html', {'customer':cnt})            
                else:
                    messages.info(request, '조회되는 쿠폰이 없습니다.')
            except:
                return HttpResponse(status=401)
        elif request.POST.get('save_coupon'):
            try:
                if Coupon.objects.filter(customer = customer, store = cafe).exists():
                    cuc = Coupon.objects.get(customer=customer, store=cafe)
                    cuc.current_cnt = cuc.current_cnt + 1
                    cuc.save()
                    messages.info(request, '쿠폰 개수 %d' %(cuc.current_cnt))
                else:
                    obj = Coupon(customer = customer, store = cafe, current_cnt = 1) 
                    obj.save()
                    messages.info(request, '쿠폰 개수 %d' %(obj.current_cnt))
            except:
                return HttpResponse(status=401)                    

            return render(request, 'aboutcoupon.html')#, {'cuc':cuc.current_cnt}            
        
        elif request.POST.get('use_coupon'):
            try:
                stamp = Manager.objects.get(cafe_name=find_cafe).cafe_stamp
                cuc = Coupon.objects.get(customer=customer, store=cafe)
                
                if cuc.current_cnt >= stamp:                
                    avail = cuc.current_cnt // stamp

                    
                    cuc.current_cnt = cuc.current_cnt - stamp
                    cuc.save()
                    messages.info(request, '사용 후 남은 쿠폰 : %d'%(cuc.current_cnt))
                else:
                    messages.info(request, '현재 쿠폰 개수 : %d' %(cuc.current_cnt))
                    return render(request, 'aboutcoupon.html', {'current_cnt':cuc.current_cnt})
            except:
                return HttpResponse(status=401)        
    contents = {'user': user, 'session_id': session_id, 'find_cafe':find_cafe}
   
    return render(request, 'aboutcoupon.html', contents)

