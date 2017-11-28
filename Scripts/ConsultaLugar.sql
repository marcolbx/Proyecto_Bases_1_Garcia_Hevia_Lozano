select l1.lug_nombre as Estado,l2.lug_nombre as Municipio, l3.lug_nombre as Parroquia from Lugar l1, Lugar l2, Lugar l3
where l3.fk_lug_codigo=l2.lug_codigo and l2.fk_lug_codigo=l1.lug_codigo 
and l3.lug_tipo = 'Parroquia' and l2.lug_tipo = 'Municipio'and l1.lug_tipo = 'Estado';