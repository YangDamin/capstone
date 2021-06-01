from django.shortcuts import render, redirect
from .models import Manager

def logout(request):
    if request.session['user']:
        del(request.session['user'])
        return redirect('/fuser/login')

def aboutcafe(request):
    if request.method == "GET":
        return render(request, 'aboutcafe.html')
    elif request.method == "POST":
        cafe_name = request.POST.get('cafe_name', None)
        cafe_stamp = request.POST.get('cafe_stamp', None)
        cafe_explain = request.POST.get('cafe_explain', None)
        longtitude = request.POST.get('longtitude', None)
        latitude = request.POST.get('latitude', None)
        phone = request.POST.get('phone', None)

        manager = Manager(
            cafe_name = cafe_name,
            cafe_stamp = cafe_stamp,
            cafe_explain = cafe_explain,
            longtitude = longtitude,
            latitude = latitude,
        )

        manager.save()
    return render(request,'aboutcafe.html')
    # return redirect('/')