from django.shortcuts import render
from django.http import JsonResponse

# Create your views here.


def hallo_welt(request):
    return JsonResponse({"res": "hallo-welt"})
