-- Table: public.bread

-- DROP TABLE IF EXISTS public.bread;

CREATE TABLE IF NOT EXISTS public.bread
(
    id character varying(36) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    displayname character varying(255) COLLATE pg_catalog."default",
    quantity real
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.bread
    OWNER to postgres;
-- Index: CI_Bread_Type

-- DROP INDEX IF EXISTS public."CI_Bread_Type";

CREATE UNIQUE INDEX IF NOT EXISTS "CI_Bread_Type"
    ON public.bread USING btree
    (type COLLATE pg_catalog."default" text_pattern_ops ASC NULLS LAST)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.bread
    CLUSTER ON "CI_Bread_Type";
-- Index: NC_Bread_Id

-- DROP INDEX IF EXISTS public."NC_Bread_Id";

CREATE UNIQUE INDEX IF NOT EXISTS "NC_Bread_Id"
    ON public.bread USING btree
    (id COLLATE pg_catalog."default" varchar_ops ASC NULLS LAST)
    INCLUDE(type, displayname, quantity)
    TABLESPACE pg_default;
	
INSERT INTO public.bread(id, type, displayName, quantity)
VALUES('ABC123', 'MilletBread', 'This is Millet Bread', 27)

INSERT INTO public.bread(id, type, displayName, quantity)
VALUES('DemoUnique', 'DemoBread', 'This is Demo Bread', 5)