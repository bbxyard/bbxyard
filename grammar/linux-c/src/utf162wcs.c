WCHAR * utf162wcs(unsigned short * utf16,WCHAR *wcs,int len)  
{  
    int utf;  
    unsigned int h;  
    unsigned int l;  
    WCHAR * ret = wcs;  
    while(utf = *utf16++)  
    {  
        if(utf<0xD800||utf>0xDFFF)  
        {  
            *wcs++ = utf;  
        }  
        else  
        {  
            h = utf - 0xD800;  
            l = *(utf16++) - 0xDC00;  
            *wcs++ =  ((h<<10) | l ) + 0x10000;  
        }  
    }  
    *wcs = 0;  
    return ret;  
}