from django.contrib import admin
from .models import Coupon

# Register your models here.
class CouponAdmin(admin.ModelAdmin):
    # pass
    list_display = ('customer','store', 'current_cnt')

admin.site.register(Coupon, CouponAdmin)
