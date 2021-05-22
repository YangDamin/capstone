from django.urls import path, include
from . import views

urlpatterns = [
    path(r'index/' , views.cntcoupon, name='index'),
    path('logout/', views.logout, name='logout'),
]
