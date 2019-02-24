# Методики расчетов

Cуществует две основные схемы погашения кредита: аннуитетная (все ежемесячные платежи равны между собой) и дифференцированная (основной долг гасится равными платежами, а проценты с каждым месяцем уменьшаются - следовательно, платёж каждый месяц тоже уменьшается). 

Рассмотрим каждую схему. 

## 1. Аннуитетная схема погашения кредита. 
**Базовые формулы:**  
k = a^n*(a - 1)/(a^n - 1) - коэффициент ежемесячного платежа, где   
a = 1 + p / 1200 - знаменатель прогрессии   
Символ ^ (крышка) означает операцию "возведение в степень".   
p - годовая процентная ставка  
n - срок кредита в месяцах.  
Ежемесячный платёж рассчитывается по формуле:  
s_m = k * c, где c - сумма кредита.  
с = стоимость товара - первый взнос  

Если банк взимает ежемесячную комиссию за ведение счёта, то её надо приплюсовать к ежемесячному платежу. 

Рассмотрим конкретные примеры.  
### Пример №1. 
**Дано:**  
Акция 10-10-10 (10% первый взнос, 10 месяцев, 10% - за пользование кредитом)  
Стоимость товара: 10000 рублей  
n = 10 (срок кредита в месяцах)  
p = 23,4% годовых (годовая процентная ставка по данной акции)  
Вычислить ежемесячный платёж.  
**Решение:**  
Первый взнос: 10000 * 10% = 1000 рублей  
Сумма кредита: c = 10000 - 1000 = 9000 рублей  
Вычислим знаменатель прогрессии: a = 1 + 23,4 / 1200 = 1,0195  
Вычислим коэффициент ежемесячного платежа:  
k = 1,0195^10 * (1,0195 - 1) / (1,0195^10 - 1) = 0,111035   
Ежемесячный платёж: s_m = k * c = 0,111035 * 9000 = 999,32 руб.  

### Пример №2.  
**Дано:**  
Банк Русский Стандарт. Стандартные условия предоставления кредитов.  
Стоимость товара: s = 10000 рублей  
Первый взнос: 10%  
Срок кредита: n = 12 месяцев  
Процентная ставка: p = 19% годовых  
Ежемесячная комиссия за ведение счёта: 1,9% от суммы первоначального кредита каждый месяц.  
**Найти:**  
1. Ежемесячный платёж.   
2. Удорожание товара.  
**Решение:**  
Первый взнос: 10000 * 10% = 1000 рублей   
Сумма кредита: c = 10000 - 1000 = 9000 рублей  
a = 1 + p / 1200 = 1,01583  
k = a^n * (a - 1) / (a^n - 1) = 1,01583^12 * (1,01583 - 1)/(1,01583^12 - 1) = 0,09215  
Ежемесячный платёж (без учёта комиссии за ведение счёта):  
s_m1 = k * c = 0,09215 * 9000 = 829,35 руб.  
Ежемесячная комиссия за ведение счёта: s_m2 = c * 1,9% = 9000 * 1,9% = 171,00 руб.  
Таким образом, ежемесячный платёж составляет:  
s_m = s_m1 + s_m2 = 829,35 + 171,00 = 1000,35 руб.  

**Как рассчитать удорожание товара? Да очень просто!**  
Сколько будет стоить товар после покупки в кредит?  
Первый взнос + ежемесячный платёж * срок кредита  
Численно:  
1000 + 1000,35 * 12 = 13004,20 руб.  

Удорожание товара: 13004,20 - 10000 = 3004,20 руб., что составляет 30,04% от первоначальной стоимости.  

## 2. Дифференцированная схема погашения кредита.Здесь всё гораздо проще.  
Ежемесячный платёж на i-м месяце вычисляется по формуле: s_i = f + p_i, где  
f = c / N - сумма в счёт погашения основного долга (одна и та же каждый месяц)  
с - сумма кредита  
N - срок кредита (месяцев)  
p_i - проценты, начисленные за пользование кредитом на i-м месяце.  
p_i = (c - f * (i - 1)) * p / 1200  
p - годовая процентная ставка.  

### Рассмотрим пример.  
**Дано:**  
Сбербанк России. Кредит на неотложные нужды.  
Сумма кредита: c = 72000 рублей  
Срок кредита: N = 36 месяцев  
Процентная ставка: p = 18% годовых  
Комиссия за открытие ссудного счёта: 1,5% от суммы кредита (разово).  
**Найти:**  
1. Ежемесячные платёжи.  
2. Плату за пользование кредитом.  
**Решение:**  
f = c / N = 72000 / 36 = 2000 рублей (т.е. каждый месяц в счёт погашения основного долга будет идти 2000 рублей).  
Первый месяц:  
p_1 = (72000 - 2000 * (1-1)) * 18 / 1200 = 1080 руб.  
s_1 = f + p_1 = 2000 + 1080 = 3080 рублей  
Второй месяц:  
p_2 = (72000 - 2000 * (2-1))* 18 / 1200 = 1050 руб.  
s_2 = f + p_2 = 2000 + 1050 = 3050 руб.  
И так далее...  

**Как вычислить плату за пользование кредитом?**   
Для этого есть одна заветная формула, которая позволяет вычислить сумму процентов, начисленных за пользование кредитом в течение N месяцев.  
s_p = p * (N + 1) / 24, где:  
p - годовая процентная ставка  
N - срок кредита (месяцев).  

Таким образом, за 36 месяцев будет начислено:  
s_p = 18 * (36 + 1) / 24 = 27,75%  

Всего за пользование кредитом заёмщик заплатит:  
27,75% (проценты по кредиту) + 1,5% (комиссия за открытие ссудного счёта) = 29,25%. 