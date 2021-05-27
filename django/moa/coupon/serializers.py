from rest_framework import serializers
from .models import Coupon

class CouponSerializer(serializers.ModelSerializer) :
    class Meta :
        model = Coupon
        fields = ['customer', 'store', 'current_cnt']