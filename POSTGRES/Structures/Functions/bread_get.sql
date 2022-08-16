CREATE OR REPLACE FUNCTION public.bread_get(IN idIn character varying, IN typeIn character varying)
    RETURNS SETOF bread
    LANGUAGE 'sql'
    
AS $BODY$
   SELECT
   	id
	, type
	, displayName
	, quantity
   FROM bread b
   WHERE COALESCE(idIn, b.id) = b.id
   AND COALESCE(typeIn, b.type) = b.type;
$BODY$;

ALTER FUNCTION public.bread_get(character varying, character varying)
    OWNER TO postgres;