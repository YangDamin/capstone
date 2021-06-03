from rest_framework import serializers
from .models import Manager

class CouponSerializer(serializers.ModelSerializer) :
    class Meta :
        model = Coupon
        fields = '__all__'