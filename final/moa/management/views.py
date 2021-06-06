from django.shortcuts import render, redirect
from .models import Manager
from django.http import HttpResponse, JsonResponse 
from .serializers import CafeSerializer
from fuser.models import Fuser


def logout(request):
    if request.session['user']:
        del(request.session['user'])
        return redirect('/fuser/login')

def aboutcafe(request):
    session_id = request.session.session_key
    user = request.session['user']


    find_cafe = Fuser.objects.get(user_id=user).cafe_name
    
    contents = {'user': user, 'session_id': session_id, 'find_cafe':find_cafe}
    try:
        if request.method == "GET":
            return render(request, 'aboutcafe.html')
        elif request.method == "POST":
            cafe_name = request.POST.get('cafe_name', None)
            cafe_stamp = request.POST.get('cafe_stamp', None)
            cafe_explain = request.POST.get('cafe_explain', None)
            longitude = request.POST.get('longitude', None)
            latitude = request.POST.get('latitude', None)
            phone = request.POST.get('phone', None)

            manager = Manager(
                cafe_name = cafe_name,
                cafe_stamp = cafe_stamp,
                cafe_explain = cafe_explain,
                longitude = longitude,
                latitude = latitude,
                phone = phone,
            )

            manager.save()

    except:
        return HttpResponse(status=100)
    return render(request,'aboutcafe.html', contents)

def cafe_list(request) :
    if request.method == "GET" :
        cafe_list = Manager.objects.all()
        serializer  = CafeSerializer(cafe_list,many=True)

        return JsonResponse(serializer.data, safe=False)