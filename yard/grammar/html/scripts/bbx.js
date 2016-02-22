function onload() {
    updateTime();
}

function updateTime() {
    var now = new Date();
    var hour = now.getHours();
    var min  = now.getMinutes();
    var sec  = now.getSeconds();
    hour = fillZeroForSinglNumber(hour);
    min  = fillZeroForSinglNumber(min);
    sec  = fillZeroForSinglNumber(sec);
    document.getElementById("time").innerHTML = hour + ":" + min + ":" + sec;
    setTimeout('updateTime()', 500);
}


function fillZeroForSinglNumber(i)
{
    if (i < 10)
    {
        i = "0" + i;
    }
    return i;
}
