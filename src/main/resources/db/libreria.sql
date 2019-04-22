CREATE SEQUENCE public.comics_codigo_seq
    INCREMENT 1
    START 11
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.comics_codigo_seq
    OWNER TO postgres;

CREATE SEQUENCE public.usuarios_id_seq
    INCREMENT 1
    START 7
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.usuarios_id_seq
    OWNER TO postgres;

-- Table: public.comics

-- DROP TABLE public.comics;

CREATE TABLE public.comics
(
    nombre text COLLATE pg_catalog."default",
    editorial text COLLATE pg_catalog."default",
    autor text COLLATE pg_catalog."default",
    codigo integer NOT NULL DEFAULT nextval('comics_codigo_seq'::regclass),
    id integer,
    resumen text COLLATE pg_catalog."default",
    genero text COLLATE pg_catalog."default",
    CONSTRAINT comics_pkey PRIMARY KEY (codigo)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.comics
    OWNER to postgres;

-- Table: public.usuarios

-- DROP TABLE public.usuarios;

CREATE TABLE public.usuarios
(
    id integer NOT NULL DEFAULT nextval('usuarios_id_seq'::regclass),
    nombre text COLLATE pg_catalog."default" NOT NULL,
    contrasenia text COLLATE pg_catalog."default" NOT NULL,
    admin boolean,
    codigo text COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default",
    apellido text COLLATE pg_catalog."default",
    CONSTRAINT usuarios_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usuarios
    OWNER to postgres;