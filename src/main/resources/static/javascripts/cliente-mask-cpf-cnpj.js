var Brewer = Brewer || {};

Brewer.MaskCpfCnpj = (function(){

    function MaskCpfCnpj(){
        this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
        this.labelCpfCnpj = $('[for=cpfcnpj]');
        this.inputCpfCnpj = $('#cpfcnpj');
    }

    MaskCpfCnpj.prototype.iniciar = function() {
        this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
        var tipoPessoaSelecionada = this.radioTipoPessoa.filter(':checked')[0];
        if(tipoPessoaSelecionada){
            aplicarMascarar.call(this, $(tipoPessoaSelecionada));
        }
    }

    function onTipoPessoaAlterado(evento){
        var tipoPessoaSelecionada = $(evento.currentTarget);
        aplicarMascarar.call(this, tipoPessoaSelecionada);
        this.inputCpfCnpj.val('');
    }

    function aplicarMascarar(tipoPessoaSelecionada) {
        this.labelCpfCnpj.text(tipoPessoaSelecionada.data('documento'));
        this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'));
        this.inputCpfCnpj.removeAttr('disabled');
    }

    return MaskCpfCnpj;

}());


$(function(){
    var maskCpfCnpj = new Brewer.MaskCpfCnpj;
    maskCpfCnpj.iniciar();
});