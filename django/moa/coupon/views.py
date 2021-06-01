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

@csrf_exempt
def coupon(request) : # in mobile app
    if request.method == "GET" :
        data = JSONParser().parser(request.data)
        try :
            obj = Coupon.object.get(customer = data['customer'],store =data['store'] )
        except :
            return HttpResponse(status=400)

        serializer = CouponSerializer(obj)
        return JsonResponse(serializer.data, status=201)


# on computer
def aboutcoupon(request):
    customers = Coupon.objects.all()
    context= {'customers':customers}
    
    session_id = request.session.session_key
    user = request.session['user']
    find_cafe = Fuser.objects.get(user_id=user).cafe_name

    if request.method == "POST":    
        find_user_id = request.POST.get('userid', None)
        find_user_phone = request.POST.get('userphone', None)
            
        customer = Coupon.objects.get(customer_id=find_user_id)
    
        if request.POST.get('look_coupon'):
            
            cnt = customer.current_cnt
            messages.add_message(request, messages.INFO,'쿠폰 개수 %d'%(cnt))
            return render(request, 'aboutcoupon.html', {'customer':customer.current_cnt})            
        
        elif request.POST.get('save_coupon'):
            customer.current_cnt = customer.current_cnt + 1
            customer.save()
            return render(request, 'aboutcoupon.html', {'customer':customer.current_cnt})            
        
        elif request.POST.get('use_coupon'):
            stamp = Manager.objects.get(cafe_name=find_cafe).cafe_stamp
    
            if customer.current_cnt >= stamp:
                avail = customer.current_cnt // stamp
                messages.info(request, '사용 가능 %d'%(avail))
                customer.current_cnt = customer.current_cnt - stamp
                customer.save()
                return render(request, 'aboutcoupon.html', {'current_cnt':customer.current_cnt})
            else:
                return render(request, 'aboutcoupon.html', {'current_cnt':customer.current_cnt})
            
    contents = {'user': user, 'session_id': session_id, 'find_cafe':find_cafe}
   
    return render(request, 'aboutcoupon.html', contents)

