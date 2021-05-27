# from django.shortcuts import render

# # Create your views here.

from django.shortcuts import render, redirect
from django.http import HttpResponse, JsonResponse 
from django.views.decorators.csrf import csrf_exempt 
from .models import Coupon
from .serializers import CouponSerializer
from rest_framework.parsers import JSONParser

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


def aboutcoupon(request):
    find_user_id = request.POST.get('userid', None)
    find_user_phone = request.POST.get('userphone', None)
        
    coupons = Coupon.objects.filter(customer_id=find_user_id)
    context = {'coupons': coupons}
    

    return render(request,'aboutcoupon.html', context)
