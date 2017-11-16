insert into brands (name) values ('audi');
insert into brands (name) values ('ford');
insert into brands (name) values ('bmw');
insert into brands (name) values ('mercedes-benz');
insert into brands (name) values ('volkswagen');
insert into brands (name) values ('toyota');

insert into car_body (name) values ('sedan');
insert into car_body (name) values ('universal');
insert into car_body (name) values ('hatchback');
insert into car_body (name) values ('coupe');
insert into car_body (name) values ('cabriolet');

insert into drive_unit (drive_unit) values ('передний');
insert into drive_unit (drive_unit) values ('задний');
insert into drive_unit (drive_unit) values ('полный');

insert into engines (name) values ('бензиновый');
insert into engines (name) values ('дизельный');

insert into gearboxes (name) values ('механическая');
insert into gearboxes (name) values ('автомат');
insert into gearboxes (name) values ('робот');
insert into gearboxes (name) values ('вариатор');

insert into models (name, brand_id) values ('A6', 1);
insert into models (name, brand_id) values ('Q3', 1);
insert into models (name, brand_id) values ('Q5', 1);
insert into models (name, brand_id) values ('TT', 1);
insert into models (name, brand_id) values ('focus', 2);
insert into models (name, brand_id) values ('fiesta', 2);
insert into models (name, brand_id) values ('mondeo', 2);
insert into models (name, brand_id) values ('explorer', 2);
insert into models (name, brand_id) values ('M5', 3);
insert into models (name, brand_id) values ('X6', 3);
insert into models (name, brand_id) values ('i8', 3);
insert into models (name, brand_id) values ('i3', 3);
insert into models (name, brand_id) values ('C class', 4);
insert into models (name, brand_id) values ('E class', 4);
insert into models (name, brand_id) values ('CLS', 4);
insert into models (name, brand_id) values ('G class', 4);
insert into models (name, brand_id) values ('passat', 5);
insert into models (name, brand_id) values ('golf', 5);
insert into models (name, brand_id) values ('amarok', 5);
insert into models (name, brand_id) values ('touareg', 5);
insert into models (name, brand_id) values ('camry', 6);
insert into models (name, brand_id) values ('RAV4', 6);
insert into models (name, brand_id) values ('Land Cruiser', 6);
insert into models (name, brand_id) values ('prius', 6);

insert into cities (name) values('Москва');
insert into cities (name) values('Санкт-Петербург');
insert into cities (name) values('Екатеринбург');
insert into cities (name) values('Нижний Новгород');