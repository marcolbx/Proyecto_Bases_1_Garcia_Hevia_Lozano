--pk = primary key
--fk= foreign key
 
create table lugar
(
	lug_codigo serial not null,
	lug_nombre varchar(40) not null,
	lug_tipo varchar(20) not null,
	fk_lug_codigo int,
	constraint pk_lugar primary key(lug_codigo),
	constraint fk_lugar foreign key(lug_codigo)
	references lugar(lug_codigo)
);


create table fabrica
(
	fab_codigo int not null,
	fab_nombre varchar(15),
	fk_lug_codigo int not null, 
	constraint pk_fabrica primary key(fab_codigo),
	constraint fk_lugar foreign key(fk_lug_codigo)
	references lugar (lug_codigo)
);


create table zona
(
	zon_codigo int not null,
	zon_nombre varchar(10) not null,
	zon_descripcion varchar(50),
	fk_fab_codigo int not null,
	constraint pk_zona primary key (zon_codigo),
	constraint u_zon_nombre unique(zon_nombre),
	constraint fk_fab_codigo foreign key(fk_fab_codigo)
	references fabrica(fab_codigo)
);

create table personal
(
	per_ci int not null,
	per_nombre varchar(15) not null,
	per_apellido_1 varchar(15) not null,
	per_apellido_2 varchar(15) not null,
	per_fecha_inicio date not null,
	per_titulacion varchar(35) not null,
	per_experiencia varchar(100) not null,
	fk_lug_codigo int not null,
	fk_zon_codigo int not null,
	constraint pk_personal primary key(per_ci),
	constraint fk_lugar foreign key(fk_lug_codigo)
	references lugar(lug_codigo),
	constraint fk_zon_codigo foreign key(fk_zon_codigo)
	references zona(zon_codigo)
);

create table beneficiario
(
	ben_ci int not null,
	ben_nombre varchar(15) not null,
	ben_apellido_1 varchar(15) not null,
	ben_apellido_2 varchar(15) not null,
	ben_parentesco varchar(15),
	fk_lug_codigo int not null,
	fk_per_ci int not null,
	constraint pk_beneficiario primary key(ben_ci),
	constraint fk_lugar foreign key(fk_lug_codigo)
	references lugar(lug_codigo),
	constraint fk_personal foreign key(fk_per_ci)
	references personal(per_ci)
);

create table proveedor
(
	pro_rif int not null,
	pro_nombre varchar(15) not null,
	pro_monto_acreditado int not null,
	pro_fecha_inicio date not null,
	fk_lug_codigo int not null,
	constraint pk_proveedor primary key(pro_rif),
	constraint fk_lugar foreign key(fk_lug_codigo)
	references lugar(lug_codigo)
);

create table cliente
(
	cli_rif int not null,
	cli_nombre varchar(15) not null,
	cli_monto_acreditado int not null,
	cli_fecha_inicio date not null,
	fk_lug_codigo int not null,
	constraint pk_cliente primary key(cli_rif),
	constraint fk_lugar foreign key(fk_lug_codigo)
	references lugar(lug_codigo)
);

create table informacion_contacto
(
	inf_codigo serial not null,
	inf_valor varchar(20) not null,
	inf_tipo varchar(15) not null,
	fk_pro_rif int,
	fk_per_ci int,
	fk_cli_rif int,
	fk_ben_ci int,
	constraint pk_informacion_contacto primary key(inf_codigo),
	constraint fk_pro_rif foreign key(fk_pro_rif)
	references proveedor (pro_rif),
	constraint fk_per_ci foreign key(fk_per_ci)
	references personal(per_ci),
	constraint fk_cli_rif foreign key(fk_cli_rif)
	references cliente(cli_rif),
	constraint fk_ben_ci foreign key(fk_ben_ci)
	references beneficiario(ben_ci)
);

create table modelo_aeronave
(
	mod_codigo int not null,
	mod_nombre varchar(30) not null,
	constraint pk_modelo_aeronave primary key(mod_codigo)
);

create table caracteristica
(
	car_codigo int not null,
	car_nombre varchar(40) not null,
	constraint pk_caracteristica primary key(car_codigo)
);

create table mod_car
(
	mod_car_codigo serial not null,
	mod_car_valor int not null,
	fk_mod_codigo int not null,
	fk_car_codigo int not null,
	constraint pk_mod_car primary key(mod_car_codigo,fk_mod_codigo,fk_car_codigo),
	constraint fk_modelo_aeronave foreign key(fk_mod_codigo)
	references modelo_aeronave(mod_codigo),
	constraint fk_caracteristica foreign key(fk_car_codigo)
	references caracteristica(car_codigo)	
);

create table aeronave
(
	aer_codigo int not null,
	aer_fecha_compra date not null,
	fk_cli_rif int not null,
	fk_mod_codigo int not null,
	constraint pk_aeronave primary key(aer_codigo),
	constraint fk_cli_rif foreign key(fk_cli_rif)
	references cliente(cli_rif),
	constraint fk_tip_cod foreign key(fk_mod_codigo)
	references modelo_aeronave(mod_codigo)
);

create table solicitud
(
	sol_codigo int not null,
	sol_completada bit not null, --estatus=1 =realizada, 0=pending
	sol_descripcion varchar(50) not null,
	fk_fab_codigo1 int not null,
	fk_fab_codigo2 int not null,
	constraint pk_solicitud primary key(sol_codigo,fk_fab_codigo1,fk_fab_codigo2),
	constraint fk_fab_codigo1 foreign key(fk_fab_codigo1)
	references fabrica (fab_codigo),
	constraint fk_fab_codigo2 foreign key(fk_fab_codigo2)
	references fabrica (fab_codigo)
);

-- hasta aqui
create table material
(
	mat_codigo serial not null,
	mat_nombre varchar(30) not null,
	constraint pk_material primary key(mat_codigo)
);


create table mat_pro
(
	mat_pro_codigo int not null,
	mat_pro_precio int not null,
	mat_pro_fecha_compra date not null,
    fk_mat_codigo int not null,
    fk_pro_rif int not null,
	constraint pk_mat_pro primary key(mat_pro_codigo),
	constraint fk_mat_codigo foreign key(fk_mat_codigo)
	references material(mat_codigo),
	constraint fk_pro_rif foreign key(fk_pro_rif)
	references proveedor(pro_rif)
);

create table tipo_pieza
(
	tip_codigo int not null,
    fk_tip_codigo int not null,
	tip_nombre varchar(30) not null,
	constraint pk_tip_pieza primary key(tip_codigo),
	constraint fk_tip_cod foreign key(fk_tip_codigo)
	references tipo_pieza(tip_codigo)
);

create table tip_mat
(
	tip_mat_codigo int not null,
    fk_mat_codigo int not null,
    fk_tip_codigo int not null,
	constraint pk_tip_mat primary key(tip_mat_codigo),
	constraint fk_mat_codigo foreign key(fk_mat_codigo)
	references material(mat_codigo),
	constraint fk_tip_codigo foreign key(fk_tip_codigo)
	references tipo_pieza(tip_codigo)
);

create table inventario
(
	inv_codigo int not null,
    fk_fab_codigo int not null,
	constraint pk_inventario primary key(inv_codigo),
	constraint fk_fabrica foreign key(fk_fab_codigo)
	references fabrica(fab_codigo)
);

create table pieza
(
	pie_codigo int not null,
	pie_fecha_estimada date,
	pie_fecha_entregada date,
	pie_estado varchar(30) not null,
    fk_fab_codigo int not null,
    fk_inv_codigo int not null,
    fk_aer_codigo int not null,
    fk_tip_codigo int not null,
    fk_pie_codigo int not null,
	constraint pk_pieza primary key(pie_codigo),
	constraint fk_fabrica foreign key(fk_fab_codigo)
	references fabrica(fab_codigo),
	constraint fk_inv_codigo foreign key(fk_inv_codigo)
	references inventario(inv_codigo),
	constraint fk_aeronave foreign key(fk_aer_codigo)
	references aeronave(aer_codigo),
	constraint fk_tip_cod foreign key(fk_tip_codigo)
	references tipo_pieza(tip_codigo),
	constraint fk_pieza foreign key(fk_pie_codigo)
	references pieza(pie_codigo)
);



create table tip_mod
(
	tip_mod_codigo int not null,
	tip_mod_cantidad int not null,
    fk_mod_codigo int not null,
    fk_tip_codigo int not null,
	constraint pk_tip_mod primary key(tip_mod_codigo),
	constraint fk_mod_codigo foreign key(fk_mod_codigo)
	references modelo_aeronave(mod_codigo),
	constraint fk_tip_codigo foreign key(fk_tip_codigo)
	references tipo_pieza(tip_codigo)
);

create table prueba
(
	pru_codigo int not null,
	pru_nombre varchar(35) not null,
	pru_descripcion varchar(90) not null,
	constraint pk_prueba primary key(pru_codigo)
);

create table pru_mat
(
		pru_mat_codigo int not null,
		pru_mat_fecha_realizacion date,
		pru_mat_estatus varchar(15) not null,
    	fk_pru_codigo int not null,
    	fk_mat_codigo int not null,
		constraint pk_pru_mat primary key(pru_mat_codigo),
		constraint fk_prueba foreign key(fk_pru_codigo)
		references prueba(pru_codigo),
		constraint fk_material foreign key(fk_mat_codigo)
		references material(mat_codigo)
);

create table pru_pie
(
	pru_pie_codigo int not null,
	pru_pie_fecha_realizacion date,
	pru_pie_estatus varchar(15) not null,
    fk_pru_codigo int not null,
    fk_pie_codigo int not null,
	constraint pk_pru_pie primary key(pru_pie_codigo),
	constraint fk_prueba foreign key(fk_pru_codigo)
	references prueba(pru_codigo),
	constraint fk_pieza foreign key(fk_pie_codigo)
	references pieza(pie_codigo)
);


create table mat_inv
(
	mat_inv_codigo int not null,
	mat_inv_cantidad int,
    fk_mat_codigo int not null,
    fk_inv_codigo int not null,
	constraint pk_mat_inv primary key(mat_inv_codigo),
	constraint fk_material foreign key(fk_mat_codigo)
	references material(mat_codigo),
	constraint fk_inv foreign key(fk_inv_codigo)
	references inventario(inv_codigo)
);

create table per_pru_pie
(
	 per_pru_pie_codigo int not null,
     fk_per_ci int not null,
     fk_pru_codigo int not null,
     fk_pie_codigo int not null,
	 constraint pk_per_pru_pie primary key(per_pru_pie_codigo),
	 constraint fk_personal foreign key(fk_per_ci)
	 references personal(per_ci),
	 constraint fk_prueba foreign key(fk_pru_codigo)
	 references prueba(pru_codigo),
	 constraint fk_pieza foreign key(fk_pie_codigo)
	 references pieza(pie_codigo)
);



