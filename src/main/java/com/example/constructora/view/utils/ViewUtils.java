package com.example.constructora.view.utils;

public class ViewUtils {
    public static final String INIT_VALUE = "----";

    public static final String DAYS[]
            = {
            INIT_VALUE,
            "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"};

    public static final String MONTHS[]
            = {
            INIT_VALUE,
            "Ener", "Feb", "Mar", "Abr",
            "May", "Jun", "Juli", "Ago",
            "Sep", "Oct", "Nov", "Dic"
    };

    public static final String DOBYEARS[]
            = {
            INIT_VALUE,
            "1954", "1955", "1956", "1957",
            "1958", "1959", "1960", "1961",
            "1962", "1963", "1964", "1965",
            "1966", "1967", "1968", "1969",
            "1970", "1971", "1972", "1973",
            "1974", "1975", "1976", "1977",
            "1976", "1977", "1978", "1979",
            "1980", "1981", "1982", "1983",
            "1984", "1985", "1986", "1987",
            "1988", "1989", "1990", "1991",
            "1992", "1993", "1994", "1995",
            "1996", "1997", "1998", "1999",
            "2000", "2001", "2002", "2003",
            "2004", "2005", "2006", "2007",
    };

    public static final String COMINGYEARS[]
            = {
            INIT_VALUE,
            "2018", "2019", "2020", "2021",
            "2022", "2023", "2024", "2025",
            "2026", "2027", "2028", "2029",
            "2030", "2031", "2032", "2033",
            "2034", "2035", "2036", "2037",
    };


    public static final String[] COLUMN_WORKER_NAMES = {
            "DNI",
            "Nombre",
            "Genero",
            "telefono",
            "email",
            "FDN",
            "direccion",
            "CatLaboral",
            "Actualizar",
            "Eliminar"
    };

    public static final String[] COLUMN_OBRAS_NAMES = {
            "Descriptor",
            "Ubicaci??n",
            "Fecha Inicio",
            "Fecha Fin",
            "Actualizar",
            "Eliminar"
    };

    public static final String[] COLUMN_PAGOS_NAMES = {
            "PagoID",
            "TrabajadorDNI",
            "nombreTrabajador",
            "descriptorObra",
            "fechaPago",
            "horas",
            "cantidad",
            "Actualizar",
            "Eliminar"
    };

    public static final String OBRAS_REPORT_PATH= "";
}
