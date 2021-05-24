from django.contrib import admin
from .models import Manager
from fuser.models import Fuser

# Register your models here.
class ManagerAdmin(admin.ModelAdmin):
    # pass
    list_display = ('cafe_name','cnt_stamp', 'cafe_explain')

admin.site.register(Manager, ManagerAdmin)
