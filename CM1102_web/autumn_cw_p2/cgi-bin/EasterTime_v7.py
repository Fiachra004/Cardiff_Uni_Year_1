#!/usr/bin/python3

import cgi, datetime
form = cgi.FieldStorage()
year = int(form.getvalue("Year"))
output_type = form.getvalue("outputType")

def computeEaster(year):
    y = year
    a = y % 19
    b, c = y // 100, y % 100
    d = b // 4
    e = b % 4
    g = (8*b+13) // 25
    h = (19 * a + b - d - g + 15) % 30
    j = c // 4
    k = c % 4
    m = (a + 11 * h) // 319
    r = (2 * e + 2 *j - k - h + m + 32) % 7
    n = (h - m + r + 90) // 25
    p = (h - m + r + n + 19) % 32
    return datetime.date(y,n,p)

def format_output(output_type = output_type):
    date = computeEaster(year)

    if len(str(date.day)) == 1:
        date_day = "0" + str(date.day)
    else:
        date_day = date.day
    if len(str(date.month)) == 1:
        date_month = "0" + str(date.month)
    else:
        date_month = date.month
    
    numerical = f"{date_day}/{date_month}/{date.year}"
    if output_type == "Numerical":
        return numerical
    elif output_type == "Verbose":
        if 4 <= date.day <= 20 or 24 <= date.day <= 30:
            suf = "<sup>th</sup>"
        else:
            suf=["<sup>st</sup>", "<sup>nd</sup>", "<sup>rd</sup>"][(date.day%10) - 1]
        return str(date.strftime(f"%-d{suf} %B %Y"))
    else:
        return str(f"{numerical} / {format_output('Verbose') }")


print('Content-Type: text/html; charset=utf-8')
print('')
print('<!DOCTYPE html>')
print('<html lang="en">')
print('<head> <title>Easter sunday</title><link rel="stylesheet" href="../style.css"></head>')
print('<body>')
print(f'<h2> {output_type} output: </h2>')
print(f'<p> Easter Sunday in {year} falls on: {format_output()} </p>')
print(f'<p><a href="../EasterTime_v7.html">Calculate again! </a>')
print('<br>')
print('<img src = "../EasterMan.gif"><img>')
print('</body>')
print('</html>')
