from django.contrib import admin
from . models import Customer


class CustomerAdmin(admin.ModelAdmin):
    list_display = ('user_id','name','phone','email')

admin.site.register(Customer, CustomerAdmin)