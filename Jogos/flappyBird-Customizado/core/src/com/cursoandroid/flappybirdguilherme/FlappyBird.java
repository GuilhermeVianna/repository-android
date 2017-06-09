package com.cursoandroid.flappybirdguilherme;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

    //SpriteBatch cria as animações(imagens,texturas) onde renderiza essas imagens
	private SpriteBatch batch;
    private Texture[] passaros;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;
    private Texture gameOver;
    private Random numeroRandomico;
    private BitmapFont fonte;
    private BitmapFont mensagem;
    private Circle passaroCirculo;
    private Rectangle canoTopoRetangulo;
    private Rectangle canoBaicoRetangulo;


    private Texture mario;
    private Texture cogumelo;
    private Texture fundoMario;
    private Texture[] fernando;
    private Texture cervejaBaixo;
    private Texture cervejaTopo;
    private Texture fundoCerveja;
    private Texture[] fernandosOver;
    private Texture sefodeu;
   // private ShapeRenderer shapeRenderer; // desenha as formas colisoes na tela

    //atributos de configuração
    private int movimento = 0;
    private float larguraDispositivo;
    private float alturaDispositivo;
    private int estadoDoJogo = 0; // 0 - jogo nao iniciado / 1 - jogo iniciado / 2 - gameOver
    private int pontuacao = 0;

    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical;
    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float deltaTime;
    private float alturaEntreCanosRandomica;
    private boolean marcouPonto;

    //camera
    private OrthographicCamera camera;
    private Viewport viewport;
    private final float VIRTUAL_WIDTH = 768;
    private final float VIRTUAL_HEIGHT = 1024;

    /*
    Método responsavel por inicializar jogo
    */
	@Override
	public void create () {

        fonte  = new BitmapFont();
        fonte.setColor(Color.RED);
        fonte.getData().setScale(6);

        mensagem = new BitmapFont();
        mensagem.setColor(Color.WHITE);
        mensagem.getData().setScale(2);

        //variaveis criadas para gerar colisoes entre as texturas
        passaroCirculo = new Circle();
        /*canoBaicoRetangulo = new Rectangle();
          canoBaicoRetangulo = new Rectangle();
          hapeRenderer = new ShapeRenderer();
          */

        numeroRandomico =  new Random();
        batch = new SpriteBatch();

        fundo = new Texture("fundo.png");
        canoBaixo = new Texture("cano_baixo.png");
        canoTopo = new Texture("cano_topo.png");
        gameOver = new Texture("game_over.png");

        //Teste Mario
        mario = new Texture("mario-jump-50x50.png");
        cogumelo = new Texture("cogumelo-60x60.png");

        fundoMario = new Texture("fundo-mario.jpg");
        fernando = new Texture[2];
        fernando[0] = new Texture("fernando1.png");
        fernando[1] = new Texture("fernando2.png");
        fundoCerveja = new Texture("fundo-cerveja.jpg");
        cervejaBaixo = new Texture("cervejaBaixo.png");
        cervejaTopo = new Texture("cervejaTopo.png");
        fernandosOver = new Texture[3];
        fernandosOver[0] = new Texture("fernando3.png");
        fernandosOver[1] = new Texture("fernando4.png");
        fernandosOver[2] = new Texture("fernando5.png");
        sefodeu = new Texture("sefodeu.png");


        //Criando arrays de imagem do passaro para animacao
        passaros = new Texture[3];
        passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");
        passaros[2] = new Texture("passaro3.png");


        camera = new OrthographicCamera();
        camera.position.set(VIRTUAL_WIDTH/2, VIRTUAL_HEIGHT/2,0);
        viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);

        //lib Gdx disponibilza o tamanho real da tela do dispositivo automaticamente
        larguraDispositivo =  VIRTUAL_WIDTH;
        alturaDispositivo = VIRTUAL_HEIGHT;
        posicaoInicialVertical = alturaDispositivo / 2;
        posicaoMovimentoCanoHorizontal = larguraDispositivo;
        espacoEntreCanos = 400;



    }

    /*
     Método responsavel por renderizar as animacoes do jogo
     */
	@Override
	public void render () {

        camera.update();


        //limpar frames de execucao
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        //deltatime gera numeros
        deltaTime = Gdx.graphics.getDeltaTime();
        //variacao += deltaTime;


        //testa para iniciar o jofo
        if(estadoDoJogo == 0){ //Se nao iniciado.
            variacao = 0;

            if(Gdx.input.justTouched()){
                estadoDoJogo = 1;
                variacao = 1;

            }

        }else{ //se jogo igual a 1 ele inicia o jogo

            velocidadeQueda ++;
            //fazendo o passaro cair
            if(posicaoInicialVertical > 0 || velocidadeQueda < 0){
                posicaoInicialVertical = posicaoInicialVertical - velocidadeQueda;
            }


            if(estadoDoJogo == 1){

                //faz os canos movimentarem
                posicaoMovimentoCanoHorizontal -= deltaTime * 300;

                /*
                metodo que realiza o toque na tela
                condicao que verifica se a tela foi tocada
                */
                if(Gdx.input.justTouched()){
                    velocidadeQueda = -15;
                    if(variacao == 1){
                        variacao = 0;
                    }else{
                        variacao = 1;
                    }
                }

                /*
                calculando repeticao de canos. Calculado pela posicao da largura do telefone
                posicao zero é a extremidade esquerda da tela.
                verifica se o cano saiu da tela
                */
                if(posicaoMovimentoCanoHorizontal < -canoTopo.getWidth()){
                    posicaoMovimentoCanoHorizontal = larguraDispositivo;
                    alturaEntreCanosRandomica = numeroRandomico.nextInt(400) - 200;
                    marcouPonto = false;

                }

                //verifica a pontuacao
                if(posicaoMovimentoCanoHorizontal < 120){
                    if(!marcouPonto){
                        pontuacao ++;
                        marcouPonto = true;
                    }

                }

            }else{//tela de game over

                if(Gdx.input.justTouched()){
                    estadoDoJogo = 0;
                    pontuacao = 0;
                    velocidadeQueda = 0;
                    posicaoInicialVertical = alturaDispositivo / 2;
                    posicaoMovimentoCanoHorizontal = larguraDispositivo;
                }


            }

        }
        //configurar dados da projecao da camera
        batch.setProjectionMatrix(camera.combined);

        //metodo de inicializacao
        batch.begin();

        /*
        draw desenha as texturas na tela;
        para jogar os textures para baixo temos que decrementar valores de sua posicao
        e para subir devemos adicionar valores a sua posicao
        */
        batch.draw(fundoCerveja, 0, 0, larguraDispositivo, alturaDispositivo);
        batch.draw(cervejaTopo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica);
        batch.draw(cervejaBaixo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 - cervejaBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomica);
        batch.draw(fernando[(int) variacao], 120, posicaoInicialVertical );
        if(Gdx.input.justTouched()){
            if(variacao == 1){
                batch.draw(fernando[0], 120, posicaoInicialVertical );
                variacao = 1;
            }else{
                batch.draw(fernando[1], 120, posicaoInicialVertical );
                variacao = 0;
            }
        }

        fonte.draw(batch, String.valueOf(pontuacao), larguraDispositivo / 2, alturaDispositivo - 50);

        if(estadoDoJogo == 2){
            variacao += deltaTime * 10;
            if(variacao > 2) variacao = 0;
            batch.draw(fernandosOver[(int) variacao], larguraDispositivo / 2 , alturaDispositivo / 2);
            batch.draw(sefodeu, larguraDispositivo / 2 - sefodeu.getWidth() / 2, alturaDispositivo / 2);
            mensagem.draw(batch,"Toque na tela pra reiniciar.", larguraDispositivo / 2 - 170, alturaDispositivo / 2 - gameOver.getHeight() / 2);


        }

        //metodo de finalizacao
        batch.end();

        //criando as formas
        passaroCirculo.set(120 + passaros[0].getWidth() / 2 , posicaoInicialVertical + passaros[0].getHeight() / 2, passaros[0].getWidth() / 2);
        canoBaicoRetangulo = new Rectangle(
                posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomica,
                canoBaixo.getWidth(), canoBaixo.getHeight()
        );
        canoTopoRetangulo = new Rectangle(
                posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica,
                canoTopo.getWidth(), canoTopo.getHeight()
        );

        //desenhar as formas a serem colididas
        //Utilizado para que nos ajude a fazer testes sobre o layout
        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); //formas preenchidas
        shapeRenderer.circle(passaroCirculo.x, passaroCirculo.y, passaroCirculo.radius);
        shapeRenderer.rect(canoBaicoRetangulo.x, canoBaicoRetangulo.y, canoBaicoRetangulo.width, canoBaicoRetangulo.height);
        shapeRenderer.rect(canoTopoRetangulo.x, canoTopoRetangulo.y, canoTopoRetangulo.width, canoTopoRetangulo.height);
        shape.setColor(Color.RED);
        shapeRenderer.end();
        */

        //teste de colisao
        if(Intersector.overlaps(passaroCirculo, canoBaicoRetangulo) || Intersector.overlaps(passaroCirculo, canoTopoRetangulo)
                || posicaoInicialVertical <=0 || posicaoInicialVertical >= alturaDispositivo){
            estadoDoJogo = 2;
        }

	}

    //chamado apos o create
    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }
}
