package app.domain.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ReportTest {

    @Test
    public void validateReport() {
        boolean realValue = Report.validateReport("This report has less than 400 words");
        assertTrue(realValue);
    }

    @Test
    public void validateReport1() {
        boolean realValue = Report.validateReport("Há tanto tempo que não os via, mas sabia que continuavam iguais. Almas relutantes sempre a caminhar em frente. Forças imparáveis realmente. O Diego ainda está em tour à volta do mundo acredito. Por esta altura já deve estar na América do Sul, mas não falei com ele esta semana. À quanto tempo já está ele em digressão? Bem, tudo começou quando ele decidiu que iria concorrer à presidência do FCP. Depois da morte do Pinto da Costa, o clube teve 2 anos bastante amargos e então, mais dedicado que nunca, Diego decidiu apresentar-se como concorrente. As coisas não correram propriamente como desejadas, bem sim e não. Como não tinha um grande reconhecimento, especialmente na área do futebol ou de gerência, ele não teve muitos votos. No entanto, ficou famoso o suficiente para um nutricionista conhecido a nível mundial lhe pregar o olho e se surpreender com a sua forma física, especialmente com a sua idade. Depois de o abordar e conhecer a sua filosofia, convidou-o a partilhar os seus métodos e estilo de vida com o mundo. Após deixar as eleições, o Diego aceitou a proposta. A partir daí tem divulgado os seus métodos quer seja por apresentações, conferências ou livros e quando vê a oportunidade, não deixa de exibir as suas pinturas e livros com os fãs. Se o Diego se está a expor ao mundo, o Lora é o exato oposto. Eu não tenho uma ideia clara de onde possa estar. Já teorizamos bastante, especialmente eu, o Onhaki e o Montes, já viemos com a possibilidade de estar a trabalhar para o governo inglês, NASA, SpaceX, o céu é o limite. Aliás, falso, estas últimas teorias até nos levaram a achar que ele pode até ter ultrapassado a beira do céu e estar no espaço, numa missão secreta do governo, qualquer que o país dele seja. o João Santos até já falou da possibilidade de Marte. Definitivamente, está a preparar algo grande e eu digo grande mesmo.Despedi-me do Ricardo, da Amanda, mandei uma piadinha ao Nuno e sai com alguma pressa do estúdio.Estava uma tarde bonita, mas duma beleza feia, uma beleza perturbadora, cegante e especialmente depressiva. Era uma paisagem aterradora, como se me fosse absorver de tão pesada que era, tal como um buraco negro, deixando me num local solitário e vazio. Mesmo assim era bonita, talvez por não ser a paisagem que estava densa e.");
        assertTrue(realValue);
    }

    @Test
    public void validateReport2() {
        boolean realValue = Report.validateReport(
                "Há tanto tempo que não os via, mas sabia que continuavam iguais. Almas relutantes sempre a caminhar em frente. Forças imparáveis realmente. O Diego ainda está em \"tour\" à volta do mundo acredito. Por esta altura já deve estar na América do Sul, mas não falei com ele esta semana. À quanto tempo já está ele em digressão? Bem, tudo começou quando ele decidiu que iria concorrer à presidência do FCP. Depois da morte do Pinto da Costa, o clube teve 2 anos bastante amargos e então, mais dedicado que nunca, Diego decidiu apresentar-se como concorrente. As coisas não correram propriamente como desejadas, bem sim e não. Como não tinha um grande reconhecimento, especialmente na área do futebol ou de gerência, ele não teve muitos votos. No entanto, ficou famoso o suficiente para um nutricionista conhecido a nível mundial lhe pregar o olho e se surpreender com a sua forma física, especialmente com a sua idade. Depois de o abordar e conhecer a sua filosofia, convidou-o a partilhar os seus métodos e estilo de vida com o mundo. Após deixar as eleições, o Diego aceitou a proposta. A partir daí tem divulgado os seus métodos quer seja por apresentações, conferências ou livros e quando vê a oportunidade, não deixa de exibir as suas pinturas e livros com os fãs. Se o Diego se está a expor ao mundo, o Lora é o exato oposto. Eu não tenho uma ideia clara de onde possa estar. Já teorizamos bastante, especialmente eu, o Onhaki e o Montes, já viemos com a possibilidade de estar a trabalhar para o governo inglês, NASA, SpaceX, o céu é o limite. Aliás, falso, estas últimas teorias até nos levaram a achar que ele pode até ter ultrapassado a beira do céu e estar no espaço, numa missão secreta do governo, qualquer que o país dele seja. o João Santos até já falou da possibilidade de Marte. Definitivamente, está a preparar algo grande e eu digo grande mesmo.\n" +
                "Despedi-me do Ricardo, da Amanda, mandei uma piadinha ao Nuno e sai com alguma pressa do estúdio.\n" +
                "Estava uma tarde bonita, mas duma beleza feia, uma beleza perturbadora, cegante e especialmente depressiva. Era uma paisagem aterradora, como se me fosse absorver de tão pesada que era, tal como um buraco negro, deixando me num local solitário e vazio. Mesmo assim era bonita, talvez por não ser a paisagem que estava densa e negra, mas sim a minha alma. Hoje fez 2 anos desde que me divorciei da Leonor. Era um sentimento indescritível, como se passasse a usar lentes de contacto escuras e a partir daí até as nuvens mais fofas e o céu mais límpido tornara se uma tortura de ver, só por esse pico de felicidade me consciencializar da situação que vivia. Mas ia vivendo. Ainda tinha objetivos, motivos.. as vezes ahaha. Sempre fui uma pessoa do momento, perdida pela própria preguiça e destinada a safar-se no momento. Tipo daquela vez que escrevi o meu discurso de agradecimento na altura em que subi ao palco"
                );
        assertFalse(realValue);
    }

    @Test
    public void getReport() {
        Report reportReal = new Report("O report", 0);
        String realValue = reportReal.getReport();
        String expectedValue = "O report";
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void addDiagnosis() {
        Report reportReal = new Report("The report", 0);
        boolean realValue = reportReal.addDiagnosis("Diagnosis");
        assertTrue(realValue);
    }

    @Test
    public void getDate() {
        Report reportReal = new Report("The report", 0);
        Date realValue = reportReal.getDate();
        assertEquals(realValue, reportReal.getDate());
        assertNotNull(realValue);
    }

    @Test
    public void getID() {
        Report reportReal = new Report("The report", 0);
        int realValue = reportReal.getId();
        int expectedValue = 0;
        assertEquals(expectedValue, realValue);
        assertNotEquals(expectedValue, realValue-1);
        assertNotEquals(expectedValue, realValue+1);
    }
}