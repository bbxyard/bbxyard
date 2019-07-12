from django.shortcuts import render
from django.http import HttpResponse


# Create your views here.


def hallo_welt(request):
    return HttpResponse("hallo welt".title())
