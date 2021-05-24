from django.db import models
from account.models import Customer
from management.models import Manager

class coupon(models.Model) :
    customer = models.ForeignKey('account.Customer',on_delete=models.CASCADE, verbose_name="유저 이름", db_column="user_id")

    store = models.ForeignKey('management.Manager',on_delete=models.CASCADE,verbose_name="카페 이름", db_column="cafe_name")
    
    current_cnt = models.PositiveIntegerField(default='0',verbose_name='현재 개수') 
    
    # def __str__(self):
    #     return self.{(customer,store):current_cnt}

    class Meta:
        db_table = "coupon"
        verbose_name = "coupon"
        verbose_name_plural = "coupon"