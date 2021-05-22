from django.shortcuts import render, redirect
from django.http import HttpResponse, HttpResponseRedirect
from django.urls import reverse
from .models import Manager
from fuser.models import Fuser

# Create your views here.
def cntcoupon(request):
    if request.method=="GET":
        return render(request, 'index.html')
    elif request.method=="POST":
        cafe_name = request.POST.get('cafe_name', None)
        cnt_stamp = request.POST.get('cnt_stamp', None)
        cafe_explain = request.POST.get('cafe_explain', None)

        manager = Manager(
            cafe_name = cafe_name,
            cnt_stamp = cnt_stamp,
            cafe_explain = cafe_explain
        )

        manager.save()
    
    return redirect('/management/index')

def logout(request):
    if request.session['user']:
        del(request.session['user'])
    return redirect('/fuser/login')

def aboutcoupon(request):
    if request.method == "get":
        return render(request, 'indexh.html')
    elif request.method == "post":
        id_phone = request.POST.get('qrcode', None)
        id, phone = id_phone.split(',')
        