from rest_framework import serializers
from .models import Manager

class CafeSerializer(serializers.ModelSerializer) :
    class Meta :
        model = Manager
        fields = ['cafe_name','latitude','longitude','phone'] 
