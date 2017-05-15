package br.com.fiap.notas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import br.com.fiap.notas.util.ArquivoDB;

public class CadastraLoginActivity extends AppCompatActivity {

    private ArquivoDB arquivoDB;
    private HashMap<String, String> mapDados;
    private EditText etNome, etSobrenome, etNascimento, etEmail, etSenha;
    private RadioGroup rgSexo;

    private final String ARQ = "dados.txt";
    private final String SP = "dados";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_login);

        arquivoDB = new ArquivoDB();
        mapDados = new HashMap<>();

        etNome = (EditText) findViewById(R.id.edtNome);
        etSobrenome = (EditText) findViewById(R.id.edtSobreNome);
        etNascimento = (EditText) findViewById(R.id.edtDataNascimento);
        etEmail = (EditText) findViewById(R.id.edtEmail);
        etSenha = (EditText) findViewById(R.id.edtSenha);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);

    }

    //Método que captura os dados do formulário e valida o correto preenchimento
    private boolean capturaDados(){
        String nome, sobrenome, nascimento, email, senha, sexo;
        boolean dadosOk = false;

        nome = etNome.getText().toString();
        sobrenome = etSobrenome.getText().toString();
        nascimento = etNascimento.getText().toString();
        email = etEmail.getText().toString();
        senha = etSenha.getText().toString();

        //Retorna o id do RadioButton que está checado no RadioGroup
        int sexoId = rgSexo.getCheckedRadioButtonId();
        //Busca o RadioButton selecionado pelo id retornado em getCheckedRadioButtonId()
        RadioButton rbSexo = (RadioButton) findViewById(sexoId);

        //Validação do preenchimento do formulário
        if( android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            !TextUtils.isEmpty(senha) &&
            !TextUtils.isEmpty(nome)  &&
            !TextUtils.isEmpty(sobrenome) &&
            !TextUtils.isEmpty(nascimento) &&
                (sexoId != -1)    ){
            dadosOk = true;

            sexo = rbSexo.getText().toString();
            mapDados.put("usuario", email);
            mapDados.put("senha", senha);
            mapDados.put("nome", nome);
            mapDados.put("sobrenome", sobrenome);
            mapDados.put("nascimento", nascimento);
            mapDados.put("sexo", sexo);
        }else{
            Toast.makeText(this, R.string.validacao_conta, Toast.LENGTH_SHORT).show();
        }//fim Validação

        return dadosOk;
    }

    public void gravarChaves(View v){
        if(capturaDados()){
            arquivoDB.gravarChaves(this,SP, mapDados);
            Toast.makeText(this, R.string.cadastro_ok, Toast.LENGTH_SHORT).show();
        }
    }

    public void excluirChaves(View v){
        if(capturaDados()){
            arquivoDB.excluirChaves(this, SP, mapDados);
            Toast.makeText(this, R.string.exclusao_ok, Toast.LENGTH_SHORT).show();
        }
    }

    public void carregarChaves(View v){
        etNome.setText(arquivoDB.retornarValor(this, SP, "nome"));
        etSobrenome.setText(arquivoDB.retornarValor(this, SP, "sobrenome"));
        etNascimento.setText(arquivoDB.retornarValor(this, SP, "nascimento"));
        etEmail.setText(arquivoDB.retornarValor(this, SP, "usuario"));
        etSenha.setText(arquivoDB.retornarValor(this, SP, "senha"));

        //Ao fazermos comparações com Strings devemos buscá-las do arquivo strings.xml
        //Para garantir a internacionalização
        if(arquivoDB.retornarValor(this, SP, "sexo")
                .equals(getString(R.string.feminino))){
           rgSexo.check(R.id.rbFeminino);
        }else{
            rgSexo.check(R.id.rbMasculino);
        }
    }

    //Verifica a existencia das chaves usuario e senha no arquivo de preferencias
    public boolean verificarChaves(View v){
        if( arquivoDB.verificarChave(this, SP, "usuario") &&
            arquivoDB.verificarChave(this, SP, "senha")){
            Toast.makeText(this, R.string.login_ok, Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(this, R.string.login_nok, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //Grava um arquivo de nome arquivo.txt com o conteúdo em string contido no HashMap
    public void gravarArquivo(View v){
        if(capturaDados()){
            try {
                arquivoDB.gravarArquivo(this, ARQ, mapDados.toString());
                Toast.makeText(this, R.string.arquivo_ok, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, R.string.arquivo_nok, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }
    }

    //Método que exibe a gravação em arquivo por meio de um Toast
    public void lerArquivo(View v){
        String txt = "";
        try {
            txt = arquivoDB.lerArquivo(this, ARQ);
        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.ler_arq_nok, Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

    public void excluirArquivo(View v){
        try {
            arquivoDB.excluirArquivo(this, ARQ);
            Toast.makeText(this, R.string.exclusao_arq_ok, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.exclusao_arq_nok, Toast.LENGTH_SHORT).show();
        }

    }
    public void fecharVoltar(View v){finish();}

}
