from rest_framework import serializers
from .models import Coupon
from management.models import Manager

class CouponSerializer(serializers.ModelSerializer) :
    store = serializers.SerializerMethodField()
    cafe_stamp = serializers.SerializerMethodField()

    class Meta :
        model = Coupon
        fields = ['store', 'current_cnt','cafe_stamp']

    def get_store(self, obj) :
        store = obj.store.cafe_name
        return store

    def get_cafe_stamp(self, obj) :
        cafe_stamp = obj.store.cafe_stamp
        return cafe_stamp

