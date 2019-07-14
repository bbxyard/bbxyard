# Generated by Django 2.2.3 on 2019-07-13 11:40

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='BuildInType',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('binary', models.BinaryField()),
                ('boolean', models.BooleanField()),
                ('null_boolean', models.NullBooleanField()),
                ('positive_small_integer', models.PositiveSmallIntegerField(db_column='age')),
                ('small_integer', models.SmallIntegerField()),
                ('positive_integer', models.PositiveIntegerField()),
                ('integer', models.IntegerField(verbose_name='11 Bytes')),
                ('big_integer', models.BigIntegerField(unique=True)),
                ('char', models.CharField(blank=True, db_index=True, help_text='varchar', max_length=1024, null=True)),
                ('text', models.TextField(help_text='这个是longtext')),
                ('date', models.DateField(auto_now=True, unique_for_date=True)),
                ('time', models.TimeField()),
                ('date_time', models.DateTimeField(auto_now_add=True, unique_for_month=True)),
                ('duration', models.DurationField()),
                ('float', models.FloatField()),
                ('decimal', models.DecimalField(decimal_places=2, max_digits=4)),
                ('email', models.EmailField(max_length=254)),
                ('image', models.ImageField(upload_to='')),
                ('file', models.FileField(upload_to='')),
                ('file_path', models.FilePathField()),
                ('url', models.URLField()),
                ('uuid', models.UUIDField()),
                ('generic_ip_address', models.GenericIPAddressField()),
            ],
        ),
    ]
