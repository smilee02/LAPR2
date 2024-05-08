package app.controller;

import app.domain.model.*;
import app.domain.store.RecordResultStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReportControllerTest {

    @Test
    public void validateReport() {
        boolean realValue = Report.validateReport("This report has less than 400 words");
        assertTrue(realValue);
    }

    @Test
    public void validateReport1() {
        boolean realValue = Report.validateReport(
                "Há tanto tempo que não os via, mas sabia que continuavam iguais. Almas relutantes sempre a caminhar em frente. Forças imparáveis realmente. O Diego ainda está em \"tour\" à volta do mundo acredito. Por esta altura já deve estar na América do Sul, mas não falei com ele esta semana. À quanto tempo já está ele em digressão? Bem, tudo começou quando ele decidiu que iria concorrer à presidência do FCP. Depois da morte do Pinto da Costa, o clube teve 2 anos bastante amargos e então, mais dedicado que nunca, Diego decidiu apresentar-se como concorrente. As coisas não correram propriamente como desejadas, bem sim e não. Como não tinha um grande reconhecimento, especialmente na área do futebol ou de gerência, ele não teve muitos votos. No entanto, ficou famoso o suficiente para um nutricionista conhecido a nível mundial lhe pregar o olho e se surpreender com a sua forma física, especialmente com a sua idade. Depois de o abordar e conhecer a sua filosofia, convidou-o a partilhar os seus métodos e estilo de vida com o mundo. Após deixar as eleições, o Diego aceitou a proposta. A partir daí tem divulgado os seus métodos quer seja por apresentações, conferências ou livros e quando vê a oportunidade, não deixa de exibir as suas pinturas e livros com os fãs. Se o Diego se está a expor ao mundo, o Lora é o exato oposto. Eu não tenho uma ideia clara de onde possa estar. Já teorizamos bastante, especialmente eu, o Onhaki e o Montes, já viemos com a possibilidade de estar a trabalhar para o governo inglês, NASA, SpaceX, o céu é o limite. Aliás, falso, estas últimas teorias até nos levaram a achar que ele pode até ter ultrapassado a beira do céu e estar no espaço, numa missão secreta do governo, qualquer que o país dele seja. o João Santos até já falou da possibilidade de Marte. Definitivamente, está a preparar algo grande e eu digo grande mesmo.\n" +
                "Despedi-me do Ricardo, da Amanda, mandei uma piadinha ao Nuno e sai com alguma pressa do estúdio.\n" +
                "Estava uma tarde bonita, mas duma beleza feia, uma beleza perturbadora, cegante e especialmente depressiva. Era uma paisagem aterradora, como se me fosse absorver de tão pesada que era, tal como um buraco negro, deixando me num local solitário e vazio. Mesmo assim era bonita, talvez por não ser a paisagem que estava densa e negra, mas sim a minha alma. Hoje fez 2 anos desde que me divorciei da Leonor. Era um sentimento indescritível, como se passasse a usar lentes de contacto escuras e a partir daí até as nuvens mais fofas e o céu mais límpido tornara se uma tortura de ver, só por esse pico de felicidade me consciencializar da situação que vivia. Mas ia vivendo. Ainda tinha objetivos, motivos.. as vezes ahaha. Sempre fui uma pessoa do momento, perdida pela própria preguiça e destinada a safar-se no momento. Tipo daquela vez que escrevi o meu discurso de agradecimento na altura em que subi ao palco"
                );
        assertFalse(realValue);
    }

    @Test
    public void saveReport() {
        ReportController reportController = new ReportController();
        List<TestParameterResult> testParameterResultList = new ArrayList<>();
        List<Parameter> parameterList = new ArrayList<>();
        String ccn = "1234567812345678";
        parameterList.add(new Parameter("BLOOD","blood","blood"));
        RegisterTest test2 = new RegisterTest(parameterList,Long.parseLong(ccn),new TypeOfTest("blood","blood","blood"),"123456789121","123456789121","abcd1");
        RecordResult recordResult = new RecordResult(test2,testParameterResultList);
        RecordResultStore.getTestResultsWithTests().add(recordResult);
        boolean realValue = reportController.saveReport(new Report("Just a casual report to save",0));
        assertTrue(realValue);
    }
}