from django import forms
from django.contrib.auth.forms import UserChangeForm
from django.contrib.auth import get_user_model

class CustomerChangeForm(UserChangeForm):
    class Meta:
        model = get_user_model()
        fields = ['cafe_name','cafe_stamp', 'cafe_explain']