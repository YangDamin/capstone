from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import Customer
from .serializers import CustomerSerializer, NoPasswordSerializer
from rest_framework.parsers import JSONParser

@csrf_exempt
def customer_list(request) :
    #회원가입
    if request.method == 'POST' :
        data = JSONParser().parse(request)
        serializer = CustomerSerializer(data=data)
        if serializer.is_valid() :
            serializer.save()
            return HttpResponse(status=201)
        return HttpResponse(status=400)
    #id 중복체크 
    elif request.method == 'GET' :
        data = JSONParser().parse(request)
        data = data['user_id']
        
        if (Customer.objects.filter(user_id=data).count()) == 0 :
            return HttpResponse(status=200)
        else :
            return HttpResponse(status=400)

@csrf_exempt
#로그인
def login(request) :
    if request.method == 'POST' :
        data = JSONParser().parse(request)
        search_id = data['user_id']
        
        try :
            obj = Customer.objects.get(user_id=search_id)
        except :
            return HttpResponse(status=404)
            #404 - id 없음


        if data['password'] == obj.password :
            serializer = NoPasswordSerializer(obj)
            return JsonResponse(serializer.data,status=200)
        else :
            return HttpResponse(status=400)
            #password 불일치

