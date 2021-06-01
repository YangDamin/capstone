from rest_framework import serializers
from .models import Coupon

class CouponSerializer(serializers.ModelSerializer) :
    class Meta :
        model = Coupon
        fields = ['num_id','customer', 'store', 'current_cnt']