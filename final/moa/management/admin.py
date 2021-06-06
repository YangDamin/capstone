from django.contrib import admin
from .models import Manager

# Register your models here.
class ManagerAdmin(admin.ModelAdmin):
    # pass
    list_display = ('cafe_name','cafe_stamp', 'cafe_explain', 'longitude', 'latitude', 'phone')

admin.site.register(Manager, ManagerAdmin)
