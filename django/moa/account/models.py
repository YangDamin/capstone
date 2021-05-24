from django.db import models

class Customer(models.Model) :
    user_id = models.CharField(max_length=64,primary_key=True,unique=True)
    password = models.CharField(max_length=64) 
    name = models.CharField(max_length=64)
    phone = models.CharField(max_length=64,unique=True)
    email = models.CharField(max_length=64,unique=True)
    birth = models.CharField(max_length=64)
    created = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.user_id

    class Meta:
        db_table = "customer"
        verbose_name = "Customer"
        verbose_name_plural = "Customer"