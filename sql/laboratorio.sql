drop database if exists laboratorio;

create database laboratorio;
use laboratorio;

create table cientificos (
dni varchar(9) primary key,
nombre nvarchar(255)
);

create table proyectos (
id char(4) primary key,
nombre nvarchar(255),
horas int
);

create table asignaciones (
cientifico varchar(9),
proyecto char(4),
primary key (cientifico, proyecto),
foreign key (cientifico) references cientificos(dni) on update cascade on delete cascade,
foreign key (proyecto) references proyectos(id) on update cascade on delete cascade
);