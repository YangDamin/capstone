from django.shortcuts import render, redirect
from .models import Manager


# Create your views here.

def logout(request):
    if request.session['user']:
        del(request.session['user'])
        return redirect('/fuser/login')

def aboutcafe(request):
    if request.method == "post":
        cafe_name = request.POST.get('cafe_name', None)
        cafe_stamp = request.POST.get('cafe_stamp', None)
        cafe_explain = request.POST.get('cafe_explain', None)

        manager = Manager(
            cafe_name = cafe_name,
            cafe_stamp = cafe_stamp,
            cafe_explain = cafe_explain,
        )

        manager.save()
    return render(request,'aboutcafe.html')