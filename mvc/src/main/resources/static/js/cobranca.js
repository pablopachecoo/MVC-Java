$(function() {
	$('.js-currency').maskMoney({
		decimal : ',',
		thousands : '.',
		allowzero : true
	});
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});
		
		response.done(function(e) {
			var codigoTitulo = botaoReceber.data('codigo');
			$('[data-role=' + codigoTitulo + ']').html('<span class="badge badge-success">' + e + '</span>')
			botaoReceber.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Erro ao receber cobrança pendente');
			
		});
		
		
		
		
	});
});