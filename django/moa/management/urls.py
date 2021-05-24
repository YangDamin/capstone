from django.urls import path, include
from . import views
from fuser.views import *

urlpatterns = [
    path('index/' , views.cafe_information, name='index'),
    path('logout/', views.logout, name='logout'),
]
