document.getElementById('btnCadastrarPessoa').addEventListener('click', async (event) => {

    const nome = document.getElementById('nome').value;
    const cpf = document.getElementById('cpf').value;
    const telefone = document.getElementById('telefone').value;

    const pessoa = {
        nome: nome,
        cpf: cpf,
        telefone: telefone
    };

    try {
        const response = await fetch('http://localhost:8080/pessoas', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(pessoa)
        });

        if (response.ok) {
            alert('Pessoa cadastrada com sucesso!');
            document.getElementById('fromPessoa').reset();
        } else {
            throw new Error('Erro ao cadastrar pessoa: ' + response.status);
        }
    } catch (error) {
        console.error('Erro ao cadastrar pessoa:', error);
    }
});

document.getElementById('btnDeletarPessoa').addEventListener('click', async (event) => {

    const cpf = document.getElementById('cpfDelete').value;

    try {
        const response = await fetch(`http://localhost:8080/pessoas/${cpf}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            alert('Pessoa deletada com sucesso!');
            document.getElementById('cpfDelete').reset();
        } else {
            throw new Error('Erro ao deletar pessoa: ' + response.status);
        }
    } catch (error) {
        console.error('Erro ao deletar pessoa:', error);
    }
});

function carregarPessoas() {
    fetch('/pessoas', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => response.json())
        .then(pessoas => {
            const tabela = document.getElementById('tabelaPessoas').getElementsByTagName('tbody')[0];

            tabela.innerHTML = '';

            pessoas.forEach(pessoa => {
                const novaLinha = tabela.insertRow();

                const celulaNome = novaLinha.insertCell(0);
                const celulaCpf = novaLinha.insertCell(1);
                const celulaTelefone = novaLinha.insertCell(2);

                celulaNome.textContent = pessoa.nome;
                celulaCpf.textContent = pessoa.cpf;
                celulaTelefone.textContent = pessoa.telefone;
            });
        })
        .catch(error => {
            console.error('Erro ao carregar a lista de pessoas:', error);
        });
}

setInterval(carregarPessoas, 5000);