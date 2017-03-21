angular.module("adExtreme").value('TiposDeAnuncio', new Enum({
    MOVEL: {
        label: "movel"
    },
    IMOVEL: {
        label: "imovel"
    },
    SERVICO: {
        label: "servico"
    },
    EMPREGO: {
        label: "emprego"
    }
}));