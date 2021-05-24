from django import forms
from .models import Manager
from moa.fuser.models import Fuser

class Multiform(forms.Form):
    action = forms.CharField(max_length=64, widget=forms.HiddenInput())

class cafeForm(Multiform):
    cnt_stamp = forms.IntegerField(label="쿠폰 개수")
    cafe_explain = forms.CharField(max_length=400, label="카페 설명")

# class qrcodeForm(Multiform):
#     save_stamp = forms.