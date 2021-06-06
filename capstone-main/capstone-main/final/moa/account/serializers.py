from rest_framework import serializers
from .models import Customer

class CustomerSerializer(serializers.ModelSerializer) :
    class Meta:
        model = Customer
        fields = ['user_id','password','name','phone','email','birth']

class NoPasswordSerializer(serializers.ModelSerializer) :
    class Meta:
        model = Customer
        fields = ['user_id','name','phone','email','birth'] 

class IdSerializer(serializers.ModelSerializer) :
    class Meta : 
        model = Customer
        fields = ['user_id'] 

class PasswordSerializer(serializers.ModelSerializer) :
    class Meta:
        model = Customer
        fields = ['password']