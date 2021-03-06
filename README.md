# CreditCalculation 
Приложение для расчета процента по кредиту.

[Требования к индивидуальным заданиям](#1)  
[Задание](#2)  
[Методика расчета](#3)  

## Требования к индивидуальным заданиям: <a name = "1"></a>

1. Проанализировать предметную область, определить необходимую функциональность разрабатываемой программы.
2. Разработать графический интерфейс программы с использованием WPF. 
3. Реализовать работу с файловой системой (загружать входные и/или выгружать выходные данные, используя файл).
4. Разработать систему справки и поддержки, информация о разработчике, должна быть доступна конечному пользователю.
5. Разработать иерархию классов не менее 6-7 классов. Необходимо применить к разработанной структуре классов принципы SOLID.
6. Разработать тестовые примеры.

## Задание: <a name = "2"></a>
Требуется написать программу, которая может быть использована для примерного расчета ежемесячных платежей по кредитам. Расчет производится на основании следующих факторов: сумма покупки, первый взнос за покупку в процентах, срок кредита, годовой процент и т.д.
Кредитные калькулятор должен имеет следующие возможности:
* Расчет дифференцированных и аннуитетных платежей по кредиту;
* Дробная процентная ставка;
* Учет обслуживания счета (в процентах от суммы кредита или от остатка кредита, может быть дробной);
* Возможность расчета срока погашения кредита при платежах по кредиту равными суммами (не аннуитет);
* Подсчет полной суммы выплат по кредиту, суммы выплаченных процентов.
* Сохранение и загрузка истории платежей;
* Подсчет процентов по кредиту в зависимости от количества дней между платежами, количества дней в году;
* Быстрый показ процентов на дату платежа;
* Возможность прогнозирования окончания платежей;
* Отображение полной суммы выплат, суммы выплаченных процентов, остатка.

![](https://github.com/YarDm/CreditCalculation/blob/dev/%D0%A0%D0%B8%D1%81%D1%83%D0%BD%D0%BE%D0%BA1.png)
## Методика расчета <a name = "3"></a>
![Использованные при написании программы методики расчета](https://github.com/YarDm/CreditCalculation/blob/master/Technique.md)
