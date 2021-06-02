from rest_framework import serializers
from .models import Coupon
from management.models import Manager

class CouponSerializer(serializers.ModelSerializer) :
    store = serializers.SerializerMethodField()
    cnt_stamp = serializers.SerializerMethodField()

    class Meta :
        model = Coupon
        fields = ['store', 'current_cnt','cnt_stamp']

    def get_store(self, obj) :
        store = obj.store.cafe_name
        return store

    def get_cnt_stamp(self, obj) :
        cnt_stamp = obj.store.cnt_stamp
        return cnt_stamp

