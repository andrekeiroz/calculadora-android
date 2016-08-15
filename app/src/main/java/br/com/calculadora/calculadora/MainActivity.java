package br.com.calculadora.calculadora;


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private List<String> eq = new ArrayList<String>();
    private String va = "";
    private EditText visor;
    private boolean res = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visor = (EditText) findViewById(R.id.visor);

        Button sinButton = (Button) findViewById(R.id.sinButton);
        sinButton.setOnClickListener(this);

        Button cosButton = (Button) findViewById(R.id.cosButton);
        cosButton.setOnClickListener(this);

        Button tanButton = (Button) findViewById(R.id.tanButton);
        tanButton.setOnClickListener(this);

        Button expButton = (Button) findViewById(R.id.expButton);
        expButton.setOnClickListener(this);

        Button raizButton = (Button) findViewById(R.id.raizButton);
        raizButton.setOnClickListener(this);

        Button logButton = (Button) findViewById(R.id.logButton);
        logButton.setOnClickListener(this);

        Button parIniButton = (Button) findViewById(R.id.parIniButton);
        parIniButton.setOnClickListener(this);

        Button parFinButton = (Button) findViewById(R.id.parFinButton);
        parFinButton.setOnClickListener(this);

        Button seteButton = (Button) findViewById(R.id.seteButton);
        seteButton.setOnClickListener(this);

        Button oitoButton = (Button) findViewById(R.id.oitoButton);
        oitoButton.setOnClickListener(this);

        Button noveButton = (Button) findViewById(R.id.noveButton);
        noveButton.setOnClickListener(this);

        Button delButton = (Button) findViewById(R.id.delButton);
        delButton.setOnClickListener(this);

        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);

        Button quatroButton = (Button) findViewById(R.id.quatroButton);
        quatroButton.setOnClickListener(this);

        Button cincoButton = (Button) findViewById(R.id.cincoButton);
        cincoButton.setOnClickListener(this);

        Button seisButton = (Button) findViewById(R.id.seisButton);
        seisButton.setOnClickListener(this);

        Button multButton = (Button) findViewById(R.id.multButton);
        multButton.setOnClickListener(this);

        Button divButton = (Button) findViewById(R.id.divButton);
        divButton.setOnClickListener(this);

        Button umButton = (Button) findViewById(R.id.umButton);
        umButton.setOnClickListener(this);

        Button doisButton = (Button) findViewById(R.id.doisButton);
        doisButton.setOnClickListener(this);

        Button tresButton = (Button) findViewById(R.id.tresButton);
        tresButton.setOnClickListener(this);

        Button maisButton = (Button) findViewById(R.id.maisButton);
        maisButton.setOnClickListener(this);

        Button menosButton = (Button) findViewById(R.id.menosButton);
        menosButton.setOnClickListener(this);

        Button zeroButton = (Button) findViewById(R.id.zeroButton);
        zeroButton.setOnClickListener(this);

        Button pontoButton = (Button) findViewById(R.id.pontoButton);
        pontoButton.setOnClickListener(this);

        Button igualButton = (Button) findViewById(R.id.igualButton);
        igualButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        setVisor(v.getTag().toString());
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<String> removePar(List<String> l) {
        int[] pos = new int[2];
        for(int i = 0; i < l.size(); i++){
            if(l.get(i).equals("(")){
                pos[0] = i;
            }else if(l.get(i).equals(")")){
                pos[1] = i;
                List<String> sl = l.subList(pos[0] + 1, pos[1]);
                System.out.println(sl);
                resolve(sl);
                System.out.println(sl);
                System.out.println(l);
                l.remove(pos[0] + 2);
                l.remove(pos[0]);
                System.out.println(l);
                i = -1;
            }
        }
        return l;
    }

    private List<String> advanceOperation(List<String> l) {
        for(int i = 0; i < l.size(); i ++){

            if(l.get(i).equals("sin") || l.get(i).equals("cos") || l.get(i).equals("tan") || l.get(i).equals("log") || l.get(i).equals("√")){
                switch(l.get(i)){
                    case "sin":
                        if(isDouble(l.get(i + 1))){
                            l.set(i, String.valueOf(Math.sin(Double.parseDouble(l.get(i + 1)))));
                            l.remove(i + 1);
                            i--;
                        }
                        break;
                    case "cos":
                        if(isDouble(l.get(i + 1))){
                            l.set(i, String.valueOf(Math.cos(Double.parseDouble(l.get(i + 1)))));
                            l.remove(i + 1);
                            i--;
                        }
                        break;
                    case "tan":
                        if(isDouble(l.get(i + 1))){
                            l.set(i, String.valueOf(Math.tan(Double.parseDouble(l.get(i + 1)))));
                            l.remove(i + 1);
                            i--;
                        }
                        break;
                    case "log":
                        if(isDouble(l.get(i + 1))){
                            l.set(i, String.valueOf(Math.log(Double.parseDouble(l.get(i + 1))) / Math.log(10.0)));
                            l.remove(i + 1);
                            i--;
                        }
                        break;
                    case "√":
                        if(isDouble(l.get(i + 1))){
                            l.set(i, String.valueOf(Math.sqrt(Double.parseDouble(l.get(i + 1)))));
                            l.remove(i + 1);
                            i--;
                        }
                        break;
                }
            }
        }
        return l;
    }

    public List<String> preOperation(List<String> l, String o) {
        for(int i = 0; i < l.size(); i++){
            if(l.get(i).equals(o)){
                String aux = operation(l.get(i), l.get(i - 1), l.get(i + 1));
                l.set(i - 1, aux);
                l.remove(i + 1);
                l.remove(i);
                i--;
            }
        }
        return l;
    }

    private String operation(String o, String ns1, String ns2) {
        double resultado = 0;
        double n1 = Double.parseDouble(ns1);
        double n2 = Double.parseDouble(ns2);
        switch (o){
            case "+":
                resultado = n1 + n2;
                break;
            case "-":
                resultado = n1 - n2;
                break;

            case "*":
                resultado = n1 * n2;
                break;
            case "/":
                if(n2 == 0){
                    Toast.makeText(this, "Erro divisão por zero", Toast.LENGTH_SHORT).show();

                }else{
                    resultado = n1 / n2;
                }
                break;
            case "^":
                resultado = Math.pow(n1, n2);
                break;
        }
        return String.valueOf(resultado);
    }

    private String resolve(List<String> l){
        while(l.size() > 1){

            if(l.contains("(")){
                removePar(l);
            }else if(l.contains("^")){
                l = preOperation(l, "^");
            }else if(l.contains("sin") || l.contains("cos") || l.contains("tan") || l.contains("log") || l.contains("√")){
                if(isDouble(l.get(l.size() - 1))){
                    l = advanceOperation(l);
                }
            }else if(l.contains("*") || l.contains("/") || l.contains("-") || l.contains("+")){
                l = preOperation(l, "*");
                l = preOperation(l, "/");
                l = preOperation(l, "+");
                l = preOperation(l, "-");
            }
        }
        return l.get(0);
    }

    private void setVisor(String string) {
        switch(string){
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
                res = false;
                if(!String.valueOf(visor.getText()).isEmpty()){
                    if(isDouble(va) || eq.get(eq.size() - 1).equals(")")){
                        visor.setText(visor.getText() + string);
                        if(!va.equals("")){
                            eq.add(va);
                        }
                        eq.add(string);
                        va = "";
                    }
                }
                break;
            case "del":
                if(!String.valueOf(visor.getText()).isEmpty()){
                    if(!va.equals("")){
                        visor.setText(String.valueOf(visor.getText()).substring(0, visor.getText().length() - 1));
                        va = va.substring(0, va.length() - 1);
                    }else if(eq.get(eq.size() - 1).equals("sin")
                            || eq.get(eq.size() - 1).equals("cos")
                            || eq.get(eq.size() - 1).equals("tan")
                            || eq.get(eq.size() - 1).equals("log")){
                        visor.setText(String.valueOf(visor.getText()).substring(0, visor.getText().length() - 3));
                        eq.remove(eq.size() - 1);
                    }else{
                        visor.setText(String.valueOf(visor.getText()).substring(0, visor.getText().length() - 1));
                        eq.remove(eq.size() - 1);
                    }
                }
                break;
            case "c":
                visor.setText("");
                eq.clear();
                va = "";
                break;
            case ")":
                if(!String.valueOf(visor.getText()).isEmpty()){
                    if(isDouble(va) || eq.get(eq.size() - 1) == ")"){
                        visor.setText(visor.getText() + string);
                        if(isDouble(va)){
                            eq.add(va);
                            va = "";
                        }
                        eq.add(string);
                    }
                }
                break;
            case "(":
            case "sin":
            case "cos":
            case "tan":
            case "log":
            case "√":
                    if(res){
                        visor.setText("");
                        va = "";
                        res = false;
                    }
                    visor.setText(visor.getText() + string);
                    if (!va.equals("")) {
                        eq.add(va);
                    }
                    eq.add(string);
                    va = "";
                break;
            case "=":
                if(!String.valueOf(visor.getText()).isEmpty()){
                    if(isDouble(va)){
                        eq.add(va);
                        va = "";
                    }
                    if(isDouble(eq.get(eq.size() - 1)) || eq.get(eq.size() - 1).equals(")")){

                        System.out.println(eq);
                        va = resolve(eq);
                        visor.setText(va);
                        eq.clear();
                        res = true;
                    }
                }

                break;
            default:
                if(res){
                    visor.setText("");
                    va = "";
                    res = false;
                }
                visor.setText(visor.getText() + string);
                va += string;
                break;
        }
        System.out.println(eq);
    }


}