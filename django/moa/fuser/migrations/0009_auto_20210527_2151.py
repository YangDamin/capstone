# Generated by Django 3.1.7 on 2021-05-27 12:51

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('fuser', '0008_auto_20210524_1611'),
    ]

    operations = [
        migrations.AlterField(
            model_name='fuser',
            name='password',
            field=models.CharField(max_length=255, verbose_name='비밀번호'),
        ),
        migrations.AlterField(
            model_name='fuser',
            name='user_email',
            field=models.CharField(max_length=255, verbose_name='이메일'),
        ),
        migrations.AlterField(
            model_name='fuser',
            name='user_id',
            field=models.CharField(max_length=255, primary_key=True, serialize=False, verbose_name='아이디'),
        ),
        migrations.AlterField(
            model_name='fuser',
            name='user_name',
            field=models.CharField(max_length=255, verbose_name='이름'),
        ),
    ]
