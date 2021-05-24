from django.urls import path, include
from . import views
from fuser.views import *

urlpatterns = [
    path('index/' , views.cntcoupon, name='index'),
    path('logout/', views.logout, name='logout'),
]
