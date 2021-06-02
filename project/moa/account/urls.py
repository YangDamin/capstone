from django.urls import path
from . import views

app_name = 'account'

urlpatterns = [
    path('login/', views.login),
    path('customer_list/',views.customer_list),
    path('customer/', views.customer),
    path('find_pw/', views.find_pw),
    path('find_id/', views.find_id)
]