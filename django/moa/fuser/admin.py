from django.contrib import admin
from .models import Fuser

# Register your models here.
class FuserAdmin(admin.ModelAdmin):
    # pass
    list_display = ('user_id', 'password', 'user_name', 'user_email')

admin.site.register(Fuser, FuserAdmin)
