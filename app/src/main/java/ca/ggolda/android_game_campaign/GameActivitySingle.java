package ca.ggolda.android_game_campaign;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by gcgol on 12/19/2016.
 */


public class GameActivitySingle extends AppCompatActivity {

    private ImageView a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16;
    private ImageView b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
    private ImageView c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16;
    private ImageView d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16;
    private ImageView e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16;
    private ImageView f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16;
    private ImageView g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16;
    private ImageView h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, h14, h15, h16;
    private ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16;
    private ImageView j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16;
    private ImageView k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11, k12, k13, k14, k15, k16;
    private ImageView l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16;
    private ImageView m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16;
    private ImageView n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16;
    private ImageView o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16;
    private ImageView p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16;

    private List<String> gamesetList;
    private String gamesetString = "";

    private List<String> boardsetList;
    private String boardsetString = "";

    private float x1, x2;
    static final int MIN_DISTANCE = 150; //TODO: figure this one out

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUsersDatabaseReference;
    private ValueEventListener mUsernameValueListener;

    private LinearLayout mBoard;


    private String turn = "red";
    private TextView currentTurn;
    private TextView yourColor;

    // always red for now
    public static String playerColor = "red";


    private String selectedUnit = "";
    private int currentPosition = 999;

    private String unitEquiptment = "none";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("users");

        yourColor = (TextView) findViewById(R.id.your_color);
        currentTurn = (TextView) findViewById(R.id.current_turn);
        mBoard = (LinearLayout) findViewById(R.id.board);

        // IF boardsetString null or "", create new random board (ground)
        if ((boardsetString == null) || (boardsetString.equals("null")) || (boardsetString.equals(""))) {
            boardsetString = "";

            for (int i = 0; i < 256; i++) {

                Random r = new Random();
                int tempInt = r.nextInt(17 - 1) + 1;
                String tempString = String.valueOf(tempInt);

                boardsetString += "" + tempString + ",";
            }
        }

        gamesetString = getResources().getString(R.string.new_gameset);

        // Find imageviews
        declareBoard();
        setBoard();

    }

    private void declareBoard() {
        a1 = (ImageView) findViewById(R.id.tile_1);
        b1 = (ImageView) findViewById(R.id.tile_2);
        c1 = (ImageView) findViewById(R.id.tile_3);
        d1 = (ImageView) findViewById(R.id.tile_4);
        e1 = (ImageView) findViewById(R.id.tile_5);
        f1 = (ImageView) findViewById(R.id.tile_6);
        g1 = (ImageView) findViewById(R.id.tile_7);
        h1 = (ImageView) findViewById(R.id.tile_8);
        i1 = (ImageView) findViewById(R.id.tile_9);
        j1 = (ImageView) findViewById(R.id.tile_10);
        k1 = (ImageView) findViewById(R.id.tile_11);
        l1 = (ImageView) findViewById(R.id.tile_12);
        m1 = (ImageView) findViewById(R.id.tile_13);
        n1 = (ImageView) findViewById(R.id.tile_14);
        o1 = (ImageView) findViewById(R.id.tile_15);
        p1 = (ImageView) findViewById(R.id.tile_16);

        a2 = (ImageView) findViewById(R.id.tile_17);
        b2 = (ImageView) findViewById(R.id.tile_18);
        c2 = (ImageView) findViewById(R.id.tile_19);
        d2 = (ImageView) findViewById(R.id.tile_20);
        e2 = (ImageView) findViewById(R.id.tile_21);
        f2 = (ImageView) findViewById(R.id.tile_22);
        g2 = (ImageView) findViewById(R.id.tile_23);
        h2 = (ImageView) findViewById(R.id.tile_24);
        i2 = (ImageView) findViewById(R.id.tile_25);
        j2 = (ImageView) findViewById(R.id.tile_26);
        k2 = (ImageView) findViewById(R.id.tile_27);
        l2 = (ImageView) findViewById(R.id.tile_28);
        m2 = (ImageView) findViewById(R.id.tile_29);
        n2 = (ImageView) findViewById(R.id.tile_30);
        o2 = (ImageView) findViewById(R.id.tile_31);
        p2 = (ImageView) findViewById(R.id.tile_32);

        a3 = (ImageView) findViewById(R.id.tile_33);
        b3 = (ImageView) findViewById(R.id.tile_34);
        c3 = (ImageView) findViewById(R.id.tile_35);
        d3 = (ImageView) findViewById(R.id.tile_36);
        e3 = (ImageView) findViewById(R.id.tile_37);
        f3 = (ImageView) findViewById(R.id.tile_38);
        g3 = (ImageView) findViewById(R.id.tile_39);
        h3 = (ImageView) findViewById(R.id.tile_40);
        i3 = (ImageView) findViewById(R.id.tile_41);
        j3 = (ImageView) findViewById(R.id.tile_42);
        k3 = (ImageView) findViewById(R.id.tile_43);
        l3 = (ImageView) findViewById(R.id.tile_44);
        m3 = (ImageView) findViewById(R.id.tile_45);
        n3 = (ImageView) findViewById(R.id.tile_46);
        o3 = (ImageView) findViewById(R.id.tile_47);
        p3 = (ImageView) findViewById(R.id.tile_48);

        a4 = (ImageView) findViewById(R.id.tile_49);
        b4 = (ImageView) findViewById(R.id.tile_50);
        c4 = (ImageView) findViewById(R.id.tile_51);
        d4 = (ImageView) findViewById(R.id.tile_52);
        e4 = (ImageView) findViewById(R.id.tile_53);
        f4 = (ImageView) findViewById(R.id.tile_54);
        g4 = (ImageView) findViewById(R.id.tile_55);
        h4 = (ImageView) findViewById(R.id.tile_56);
        i4 = (ImageView) findViewById(R.id.tile_57);
        j4 = (ImageView) findViewById(R.id.tile_58);
        k4 = (ImageView) findViewById(R.id.tile_59);
        l4 = (ImageView) findViewById(R.id.tile_60);
        m4 = (ImageView) findViewById(R.id.tile_61);
        n4 = (ImageView) findViewById(R.id.tile_62);
        o4 = (ImageView) findViewById(R.id.tile_63);
        p4 = (ImageView) findViewById(R.id.tile_64);

        a5 = (ImageView) findViewById(R.id.tile_65);
        b5 = (ImageView) findViewById(R.id.tile_66);
        c5 = (ImageView) findViewById(R.id.tile_67);
        d5 = (ImageView) findViewById(R.id.tile_68);
        e5 = (ImageView) findViewById(R.id.tile_69);
        f5 = (ImageView) findViewById(R.id.tile_70);
        g5 = (ImageView) findViewById(R.id.tile_71);
        h5 = (ImageView) findViewById(R.id.tile_72);
        i5 = (ImageView) findViewById(R.id.tile_73);
        j5 = (ImageView) findViewById(R.id.tile_74);
        k5 = (ImageView) findViewById(R.id.tile_75);
        l5 = (ImageView) findViewById(R.id.tile_76);
        m5 = (ImageView) findViewById(R.id.tile_77);
        n5 = (ImageView) findViewById(R.id.tile_78);
        o5 = (ImageView) findViewById(R.id.tile_79);
        p5 = (ImageView) findViewById(R.id.tile_80);

        a6 = (ImageView) findViewById(R.id.tile_81);
        b6 = (ImageView) findViewById(R.id.tile_82);
        c6 = (ImageView) findViewById(R.id.tile_83);
        d6 = (ImageView) findViewById(R.id.tile_84);
        e6 = (ImageView) findViewById(R.id.tile_85);
        f6 = (ImageView) findViewById(R.id.tile_86);
        g6 = (ImageView) findViewById(R.id.tile_87);
        h6 = (ImageView) findViewById(R.id.tile_88);
        i6 = (ImageView) findViewById(R.id.tile_89);
        j6 = (ImageView) findViewById(R.id.tile_90);
        k6 = (ImageView) findViewById(R.id.tile_91);
        l6 = (ImageView) findViewById(R.id.tile_92);
        m6 = (ImageView) findViewById(R.id.tile_93);
        n6 = (ImageView) findViewById(R.id.tile_94);
        o6 = (ImageView) findViewById(R.id.tile_95);
        p6 = (ImageView) findViewById(R.id.tile_96);

        a7 = (ImageView) findViewById(R.id.tile_97);
        b7 = (ImageView) findViewById(R.id.tile_98);
        c7 = (ImageView) findViewById(R.id.tile_99);
        d7 = (ImageView) findViewById(R.id.tile_100);
        e7 = (ImageView) findViewById(R.id.tile_101);
        f7 = (ImageView) findViewById(R.id.tile_102);
        g7 = (ImageView) findViewById(R.id.tile_103);
        h7 = (ImageView) findViewById(R.id.tile_104);
        i7 = (ImageView) findViewById(R.id.tile_105);
        j7 = (ImageView) findViewById(R.id.tile_106);
        k7 = (ImageView) findViewById(R.id.tile_107);
        l7 = (ImageView) findViewById(R.id.tile_108);
        m7 = (ImageView) findViewById(R.id.tile_109);
        n7 = (ImageView) findViewById(R.id.tile_110);
        o7 = (ImageView) findViewById(R.id.tile_111);
        p7 = (ImageView) findViewById(R.id.tile_112);

        a8 = (ImageView) findViewById(R.id.tile_113);
        b8 = (ImageView) findViewById(R.id.tile_114);
        c8 = (ImageView) findViewById(R.id.tile_115);
        d8 = (ImageView) findViewById(R.id.tile_116);
        e8 = (ImageView) findViewById(R.id.tile_117);
        f8 = (ImageView) findViewById(R.id.tile_118);
        g8 = (ImageView) findViewById(R.id.tile_119);
        h8 = (ImageView) findViewById(R.id.tile_120);
        i8 = (ImageView) findViewById(R.id.tile_121);
        j8 = (ImageView) findViewById(R.id.tile_122);
        k8 = (ImageView) findViewById(R.id.tile_123);
        l8 = (ImageView) findViewById(R.id.tile_124);
        m8 = (ImageView) findViewById(R.id.tile_125);
        n8 = (ImageView) findViewById(R.id.tile_126);
        o8 = (ImageView) findViewById(R.id.tile_127);
        p8 = (ImageView) findViewById(R.id.tile_128);

        a9 = (ImageView) findViewById(R.id.tile_129);
        b9 = (ImageView) findViewById(R.id.tile_130);
        c9 = (ImageView) findViewById(R.id.tile_131);
        d9 = (ImageView) findViewById(R.id.tile_132);
        e9 = (ImageView) findViewById(R.id.tile_133);
        f9 = (ImageView) findViewById(R.id.tile_134);
        g9 = (ImageView) findViewById(R.id.tile_135);
        h9 = (ImageView) findViewById(R.id.tile_136);
        i9 = (ImageView) findViewById(R.id.tile_137);
        j9 = (ImageView) findViewById(R.id.tile_138);
        k9 = (ImageView) findViewById(R.id.tile_139);
        l9 = (ImageView) findViewById(R.id.tile_140);
        m9 = (ImageView) findViewById(R.id.tile_141);
        n9 = (ImageView) findViewById(R.id.tile_142);
        o9 = (ImageView) findViewById(R.id.tile_143);
        p9 = (ImageView) findViewById(R.id.tile_144);

        a10 = (ImageView) findViewById(R.id.tile_145);
        b10 = (ImageView) findViewById(R.id.tile_146);
        c10 = (ImageView) findViewById(R.id.tile_147);
        d10 = (ImageView) findViewById(R.id.tile_148);
        e10 = (ImageView) findViewById(R.id.tile_149);
        f10 = (ImageView) findViewById(R.id.tile_150);
        g10 = (ImageView) findViewById(R.id.tile_151);
        h10 = (ImageView) findViewById(R.id.tile_152);
        i10 = (ImageView) findViewById(R.id.tile_153);
        j10 = (ImageView) findViewById(R.id.tile_154);
        k10 = (ImageView) findViewById(R.id.tile_155);
        l10 = (ImageView) findViewById(R.id.tile_156);
        m10 = (ImageView) findViewById(R.id.tile_157);
        n10 = (ImageView) findViewById(R.id.tile_158);
        o10 = (ImageView) findViewById(R.id.tile_159);
        p10 = (ImageView) findViewById(R.id.tile_160);

        a11 = (ImageView) findViewById(R.id.tile_161);
        b11 = (ImageView) findViewById(R.id.tile_162);
        c11 = (ImageView) findViewById(R.id.tile_163);
        d11 = (ImageView) findViewById(R.id.tile_164);
        e11 = (ImageView) findViewById(R.id.tile_165);
        f11 = (ImageView) findViewById(R.id.tile_166);
        g11 = (ImageView) findViewById(R.id.tile_167);
        h11 = (ImageView) findViewById(R.id.tile_168);
        i11 = (ImageView) findViewById(R.id.tile_169);
        j11 = (ImageView) findViewById(R.id.tile_170);
        k11 = (ImageView) findViewById(R.id.tile_171);
        l11 = (ImageView) findViewById(R.id.tile_172);
        m11 = (ImageView) findViewById(R.id.tile_173);
        n11 = (ImageView) findViewById(R.id.tile_174);
        o11 = (ImageView) findViewById(R.id.tile_175);
        p11 = (ImageView) findViewById(R.id.tile_176);

        a12 = (ImageView) findViewById(R.id.tile_177);
        b12 = (ImageView) findViewById(R.id.tile_178);
        c12 = (ImageView) findViewById(R.id.tile_179);
        d12 = (ImageView) findViewById(R.id.tile_180);
        e12 = (ImageView) findViewById(R.id.tile_181);
        f12 = (ImageView) findViewById(R.id.tile_182);
        g12 = (ImageView) findViewById(R.id.tile_183);
        h12 = (ImageView) findViewById(R.id.tile_184);
        i12 = (ImageView) findViewById(R.id.tile_185);
        j12 = (ImageView) findViewById(R.id.tile_186);
        k12 = (ImageView) findViewById(R.id.tile_187);
        l12 = (ImageView) findViewById(R.id.tile_188);
        m12 = (ImageView) findViewById(R.id.tile_189);
        n12 = (ImageView) findViewById(R.id.tile_190);
        o12 = (ImageView) findViewById(R.id.tile_191);
        p12 = (ImageView) findViewById(R.id.tile_192);

        a13 = (ImageView) findViewById(R.id.tile_193);
        b13 = (ImageView) findViewById(R.id.tile_194);
        c13 = (ImageView) findViewById(R.id.tile_195);
        d13 = (ImageView) findViewById(R.id.tile_196);
        e13 = (ImageView) findViewById(R.id.tile_197);
        f13 = (ImageView) findViewById(R.id.tile_198);
        g13 = (ImageView) findViewById(R.id.tile_199);
        h13 = (ImageView) findViewById(R.id.tile_200);
        i13 = (ImageView) findViewById(R.id.tile_201);
        j13 = (ImageView) findViewById(R.id.tile_202);
        k13 = (ImageView) findViewById(R.id.tile_203);
        l13 = (ImageView) findViewById(R.id.tile_204);
        m13 = (ImageView) findViewById(R.id.tile_205);
        n13 = (ImageView) findViewById(R.id.tile_206);
        o13 = (ImageView) findViewById(R.id.tile_207);
        p13 = (ImageView) findViewById(R.id.tile_208);

        a14 = (ImageView) findViewById(R.id.tile_209);
        b14 = (ImageView) findViewById(R.id.tile_210);
        c14 = (ImageView) findViewById(R.id.tile_211);
        d14 = (ImageView) findViewById(R.id.tile_212);
        e14 = (ImageView) findViewById(R.id.tile_213);
        f14 = (ImageView) findViewById(R.id.tile_214);
        g14 = (ImageView) findViewById(R.id.tile_215);
        h14 = (ImageView) findViewById(R.id.tile_216);
        i14 = (ImageView) findViewById(R.id.tile_217);
        j14 = (ImageView) findViewById(R.id.tile_218);
        k14 = (ImageView) findViewById(R.id.tile_219);
        l14 = (ImageView) findViewById(R.id.tile_220);
        m14 = (ImageView) findViewById(R.id.tile_221);
        n14 = (ImageView) findViewById(R.id.tile_222);
        o14 = (ImageView) findViewById(R.id.tile_223);
        p14 = (ImageView) findViewById(R.id.tile_224);

        a15 = (ImageView) findViewById(R.id.tile_225);
        b15 = (ImageView) findViewById(R.id.tile_226);
        c15 = (ImageView) findViewById(R.id.tile_227);
        d15 = (ImageView) findViewById(R.id.tile_228);
        e15 = (ImageView) findViewById(R.id.tile_229);
        f15 = (ImageView) findViewById(R.id.tile_230);
        g15 = (ImageView) findViewById(R.id.tile_231);
        h15 = (ImageView) findViewById(R.id.tile_232);
        i15 = (ImageView) findViewById(R.id.tile_233);
        j15 = (ImageView) findViewById(R.id.tile_234);
        k15 = (ImageView) findViewById(R.id.tile_235);
        l15 = (ImageView) findViewById(R.id.tile_236);
        m15 = (ImageView) findViewById(R.id.tile_237);
        n15 = (ImageView) findViewById(R.id.tile_238);
        o15 = (ImageView) findViewById(R.id.tile_239);
        p15 = (ImageView) findViewById(R.id.tile_240);

        a16 = (ImageView) findViewById(R.id.tile_241);
        b16 = (ImageView) findViewById(R.id.tile_242);
        c16 = (ImageView) findViewById(R.id.tile_243);
        d16 = (ImageView) findViewById(R.id.tile_244);
        e16 = (ImageView) findViewById(R.id.tile_245);
        f16 = (ImageView) findViewById(R.id.tile_246);
        g16 = (ImageView) findViewById(R.id.tile_247);
        h16 = (ImageView) findViewById(R.id.tile_248);
        i16 = (ImageView) findViewById(R.id.tile_249);
        j16 = (ImageView) findViewById(R.id.tile_250);
        k16 = (ImageView) findViewById(R.id.tile_251);
        l16 = (ImageView) findViewById(R.id.tile_252);
        m16 = (ImageView) findViewById(R.id.tile_253);
        n16 = (ImageView) findViewById(R.id.tile_254);
        o16 = (ImageView) findViewById(R.id.tile_255);
        p16 = (ImageView) findViewById(R.id.tile_256);

    }

    // Set up the board, which also restricts play to current turn
    // Check gameset + playercolor + turncolor
    private void setBoard() {

        boardsetList = Arrays.asList(boardsetString.split("\\s*,\\s*"));
        gamesetList = Arrays.asList(gamesetString.split("\\s*,\\s*"));

        // For coloring starting positions
        for (int i = 0; i < gamesetList.size(); i++) {
            if (gamesetList.get(i).equals("red_none")) {
                boardsetList.set(i, "-1");
            } else if (gamesetList.get(i).equals("blue_none")) {
                boardsetList.set(i, "-2");
            }

        }

        checkWin();

        if (playerColor.equals("red") && turn.equals("red")) {
            for (int i = 0; i < gamesetList.size(); i++) {
                if (gamesetList.get(i).equals("red_none")) {
                    final int localI = i;
                    getSquareImageView(i).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            onSelected(localI);
                        }
                    });
                }

            }

        } else {
            // TODO: COMPUTER MOVES HERE I THINK
            //

            Log.e("GameActivitySingle", "I M Computa");

            for (int i = 0; i < gamesetList.size(); i++) {
                if (gamesetList.get(i).equals("blue_none")) {

                    Log.e("Computa", "" + gamesetList.get(i));

                    currentPosition = i;
                    selectedUnit = gamesetList.get(i);

                    Random compRand = new Random();

                    int computerPath = compRand.nextInt(5 - 1) + 1;

                    Log.e("Computa", "computerPath " + computerPath);

                    switch (computerPath) {
                        case 1:
                            Log.e("Computa", "upOne " + upOne(currentPosition));

                            moveTo(upOne(currentPosition));
                            break;
                        case 2:
                            Log.e("Computa", "downOne " + downOne(currentPosition));

                            moveTo(downOne(currentPosition));
                            break;
                        case 3:
                            Log.e("Computa", "rightOne " + rightOne(currentPosition));

                            moveTo(rightOne(currentPosition));
                            break;
                        case 4:
                            Log.e("Computa", "leftOne " + leftOne(currentPosition));

                            moveTo(leftOne(currentPosition));
                            break;
                    }
                }
            }
        }


        //set board color based on boardsetList
        for (int i = 0; i < boardsetList.size(); i++) {

            int color;

            if (!(boardsetList.get(i).equals(""))) {
                color = getBackgroundColor(Integer.valueOf(boardsetList.get(i)));
            } else {
                color = Color.parseColor("#000000");
            }

            if (getSquareImageView(i) != null) {
                getSquareImageView(i).setBackgroundColor(color);

                // if gameset not null or freespace, then draw the sprite
                if (!((gamesetList == null) || (gamesetList.get(i).equals("free_space")))) {
                    getSquareImageView(i).setImageResource(getResources().getIdentifier(gamesetList.get(i), "drawable", getPackageName()));

                }
            }
        }

    }

    private int getBackgroundColor(int c) {

        int color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor1);

        switch (c) {
            case -2:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColorBlue);
                break;
            case -1:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColorRed);
                break;
            case 0:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColorBlack);
                break;
            case 1:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor1);
                break;
            case 2:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor2);
                break;
            case 3:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor3);
                break;
            case 4:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor4);
                break;
            case 5:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor5);
                break;
            case 6:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor6);
                break;
            case 7:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor7);
                break;
            case 8:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor8);
                break;
            case 9:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor9);
                break;
            case 10:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor10);
                break;
            case 11:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor11);
                break;
            case 12:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor12);
                break;
            case 13:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor13);
                break;
            case 14:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor14);
                break;
            case 15:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor15);
                break;
            case 16:
                color = ContextCompat.getColor(GameActivitySingle.this, R.color.boardColor16);
                break;

        }

        return color;

    }

    private ImageView getSquareImageView(int square) {
        ImageView imgView = null;
        switch (square) {
            case 0:
                imgView = a1;
                break;
            case 1:
                imgView = b1;
                break;
            case 2:
                imgView = c1;
                break;
            case 3:
                imgView = d1;
                break;
            case 4:
                imgView = e1;
                break;
            case 5:
                imgView = f1;
                break;
            case 6:
                imgView = g1;
                break;
            case 7:
                imgView = h1;
                break;

            case 8:
                imgView = i1;
                break;
            case 9:
                imgView = j1;
                break;
            case 10:
                imgView = k1;
                break;
            case 11:
                imgView = l1;
                break;
            case 12:
                imgView = m1;
                break;
            case 13:
                imgView = n1;
                break;
            case 14:
                imgView = o1;
                break;
            case 15:
                imgView = p1;
                break;

            case 16:
                imgView = a2;
                break;
            case 17:
                imgView = b2;
                break;
            case 18:
                imgView = c2;
                break;
            case 19:
                imgView = d2;
                break;
            case 20:
                imgView = e2;
                break;
            case 21:
                imgView = f2;
                break;
            case 22:
                imgView = g2;
                break;
            case 23:
                imgView = h2;
                break;

            case 24:
                imgView = i2;
                break;
            case 25:
                imgView = j2;
                break;
            case 26:
                imgView = k2;
                break;
            case 27:
                imgView = l2;
                break;
            case 28:
                imgView = m2;
                break;
            case 29:
                imgView = n2;
                break;
            case 30:
                imgView = o2;
                break;
            case 31:
                imgView = p2;
                break;

            case 32:
                imgView = a3;
                break;
            case 33:
                imgView = b3;
                break;
            case 34:
                imgView = c3;
                break;
            case 35:
                imgView = d3;
                break;
            case 36:
                imgView = e3;
                break;
            case 37:
                imgView = f3;
                break;
            case 38:
                imgView = g3;
                break;
            case 39:
                imgView = h3;
                break;

            case 40:
                imgView = i3;
                break;
            case 41:
                imgView = j3;
                break;
            case 42:
                imgView = k3;
                break;
            case 43:
                imgView = l3;
                break;
            case 44:
                imgView = m3;
                break;
            case 45:
                imgView = n3;
                break;
            case 46:
                imgView = o3;
                break;
            case 47:
                imgView = p3;
                break;

            case 48:
                imgView = a4;
                break;
            case 49:
                imgView = b4;
                break;
            case 50:
                imgView = c4;
                break;
            case 51:
                imgView = d4;
                break;
            case 52:
                imgView = e4;
                break;
            case 53:
                imgView = f4;
                break;
            case 54:
                imgView = g4;
                break;
            case 55:
                imgView = h4;
                break;

            case 56:
                imgView = i4;
                break;
            case 57:
                imgView = j4;
                break;
            case 58:
                imgView = k4;
                break;
            case 59:
                imgView = l4;
                break;
            case 60:
                imgView = m4;
                break;
            case 61:
                imgView = n4;
                break;
            case 62:
                imgView = o4;
                break;
            case 63:
                imgView = p4;
                break;

            //

            case 64:
                imgView = a5;
                break;
            case 65:
                imgView = b5;
                break;
            case 66:
                imgView = c5;
                break;
            case 67:
                imgView = d5;
                break;
            case 68:
                imgView = e5;
                break;
            case 69:
                imgView = f5;
                break;
            case 70:
                imgView = g5;
                break;
            case 71:
                imgView = h5;
                break;

            case 72:
                imgView = i5;
                break;
            case 73:
                imgView = j5;
                break;
            case 74:
                imgView = k5;
                break;
            case 75:
                imgView = l5;
                break;
            case 76:
                imgView = m5;
                break;
            case 77:
                imgView = n5;
                break;
            case 78:
                imgView = o5;
                break;
            case 79:
                imgView = p5;
                break;

            //

            case 80:
                imgView = a6;
                break;
            case 81:
                imgView = b6;
                break;
            case 82:
                imgView = c6;
                break;
            case 83:
                imgView = d6;
                break;
            case 84:
                imgView = e6;
                break;
            case 85:
                imgView = f6;
                break;
            case 86:
                imgView = g6;
                break;
            case 87:
                imgView = h6;
                break;

            case 88:
                imgView = i6;
                break;
            case 89:
                imgView = j6;
                break;
            case 90:
                imgView = k6;
                break;
            case 91:
                imgView = l6;
                break;
            case 92:
                imgView = m6;
                break;
            case 93:
                imgView = n6;
                break;
            case 94:
                imgView = o6;
                break;
            case 95:
                imgView = p6;
                break;

            case 96:
                imgView = a7;
                break;
            case 97:
                imgView = b7;
                break;
            case 98:
                imgView = c7;
                break;
            case 99:
                imgView = d7;
                break;
            case 100:
                imgView = e7;
                break;
            case 101:
                imgView = f7;
                break;
            case 102:
                imgView = g7;
                break;
            case 103:
                imgView = h7;
                break;

            case 104:
                imgView = i7;
                break;
            case 105:
                imgView = j7;
                break;
            case 106:
                imgView = k7;
                break;
            case 107:
                imgView = l7;
                break;
            case 108:
                imgView = m7;
                break;
            case 109:
                imgView = n7;
                break;
            case 110:
                imgView = o7;
                break;
            case 111:
                imgView = p7;
                break;

            case 112:
                imgView = a8;
                break;
            case 113:
                imgView = b8;
                break;
            case 114:
                imgView = c8;
                break;
            case 115:
                imgView = d8;
                break;
            case 116:
                imgView = e8;
                break;
            case 117:
                imgView = f8;
                break;
            case 118:
                imgView = g8;
                break;
            case 119:
                imgView = h8;
                break;

            case 120:
                imgView = i8;
                break;
            case 121:
                imgView = j8;
                break;
            case 122:
                imgView = k8;
                break;
            case 123:
                imgView = l8;
                break;
            case 124:
                imgView = m8;
                break;
            case 125:
                imgView = n8;
                break;
            case 126:
                imgView = o8;
                break;
            case 127:
                imgView = p8;
                break;

            case 128:
                imgView = a9;
                break;
            case 129:
                imgView = b9;
                break;
            case 130:
                imgView = c9;
                break;
            case 131:
                imgView = d9;
                break;
            case 132:
                imgView = e9;
                break;
            case 133:
                imgView = f9;
                break;
            case 134:
                imgView = g9;
                break;
            case 135:
                imgView = h9;
                break;

            case 136:
                imgView = i9;
                break;
            case 137:
                imgView = j9;
                break;
            case 138:
                imgView = k9;
                break;
            case 139:
                imgView = l9;
                break;
            case 140:
                imgView = m9;
                break;
            case 141:
                imgView = n9;
                break;
            case 142:
                imgView = o9;
                break;
            case 143:
                imgView = p9;
                break;

            case 144:
                imgView = a10;
                break;
            case 145:
                imgView = b10;
                break;
            case 146:
                imgView = c10;
                break;
            case 147:
                imgView = d10;
                break;
            case 148:
                imgView = e10;
                break;
            case 149:
                imgView = f10;
                break;
            case 150:
                imgView = g10;
                break;
            case 151:
                imgView = h10;
                break;

            case 152:
                imgView = i10;
                break;
            case 153:
                imgView = j10;
                break;
            case 154:
                imgView = k10;
                break;
            case 155:
                imgView = l10;
                break;
            case 156:
                imgView = m10;
                break;
            case 157:
                imgView = n10;
                break;
            case 158:
                imgView = o10;
                break;
            case 159:
                imgView = p10;
                break;

            case 160:
                imgView = a11;
                break;
            case 161:
                imgView = b11;
                break;
            case 162:
                imgView = c11;
                break;
            case 163:
                imgView = d11;
                break;
            case 164:
                imgView = e11;
                break;
            case 165:
                imgView = f11;
                break;
            case 166:
                imgView = g11;
                break;
            case 167:
                imgView = h11;
                break;

            case 168:
                imgView = i11;
                break;
            case 169:
                imgView = j11;
                break;
            case 170:
                imgView = k11;
                break;
            case 171:
                imgView = l11;
                break;
            case 172:
                imgView = m11;
                break;
            case 173:
                imgView = n11;
                break;
            case 174:
                imgView = o11;
                break;
            case 175:
                imgView = p11;
                break;

            case 176:
                imgView = a12;
                break;
            case 177:
                imgView = b12;
                break;
            case 178:
                imgView = c12;
                break;
            case 179:
                imgView = d12;
                break;

            case 180:
                imgView = e12;
                break;
            case 181:
                imgView = f12;
                break;
            case 182:
                imgView = g12;
                break;
            case 183:
                imgView = h12;
                break;

            case 184:
                imgView = i12;
                break;
            case 185:
                imgView = j12;
                break;
            case 186:
                imgView = k12;
                break;
            case 187:
                imgView = l12;
                break;
            case 188:
                imgView = m12;
                break;
            case 189:
                imgView = n12;
                break;
            case 190:
                imgView = o12;
                break;
            case 191:
                imgView = p12;
                break;

            case 192:
                imgView = a13;
                break;
            case 193:
                imgView = b13;
                break;
            case 194:
                imgView = c13;
                break;
            case 195:
                imgView = d13;
                break;
            case 196:
                imgView = e13;
                break;
            case 197:
                imgView = f13;
                break;
            case 198:
                imgView = g13;
                break;
            case 199:
                imgView = h13;
                break;

            case 200:
                imgView = i13;
                break;
            case 201:
                imgView = j13;
                break;
            case 202:
                imgView = k13;
                break;
            case 203:
                imgView = l13;
                break;
            case 204:
                imgView = m13;
                break;
            case 205:
                imgView = n13;
                break;
            case 206:
                imgView = o13;
                break;
            case 207:
                imgView = p13;
                break;

            case 208:
                imgView = a14;
                break;
            case 209:
                imgView = b14;
                break;
            case 210:
                imgView = c14;
                break;
            case 211:
                imgView = d14;
                break;
            case 212:
                imgView = e14;
                break;
            case 213:
                imgView = f14;
                break;
            case 214:
                imgView = g14;
                break;
            case 215:
                imgView = h14;
                break;

            case 216:
                imgView = i14;
                break;
            case 217:
                imgView = j14;
                break;
            case 218:
                imgView = k14;
                break;
            case 219:
                imgView = l14;
                break;
            case 220:
                imgView = m14;
                break;
            case 221:
                imgView = n14;
                break;
            case 222:
                imgView = o14;
                break;
            case 223:
                imgView = p14;
                break;

            case 224:
                imgView = a15;
                break;
            case 225:
                imgView = b15;
                break;
            case 226:
                imgView = c15;
                break;
            case 227:
                imgView = d15;
                break;
            case 228:
                imgView = e15;
                break;
            case 229:
                imgView = f15;
                break;
            case 230:
                imgView = g15;
                break;
            case 231:
                imgView = h15;
                break;

            case 232:
                imgView = i15;
                break;
            case 233:
                imgView = j15;
                break;
            case 234:
                imgView = k15;
                break;
            case 235:
                imgView = l15;
                break;
            case 236:
                imgView = m15;
                break;
            case 237:
                imgView = n15;
                break;
            case 238:
                imgView = o15;
                break;
            case 239:
                imgView = p15;
                break;

            case 240:
                imgView = a16;
                break;
            case 241:
                imgView = b16;
                break;
            case 242:
                imgView = c16;
                break;
            case 243:
                imgView = d16;
                break;
            case 244:
                imgView = e16;
                break;
            case 245:
                imgView = f16;
                break;
            case 246:
                imgView = g16;
                break;
            case 247:
                imgView = h16;
                break;

            case 248:
                imgView = i16;
                break;
            case 249:
                imgView = j16;
                break;
            case 250:
                imgView = k16;
                break;
            case 251:
                imgView = l16;
                break;
            case 252:
                imgView = m16;
                break;
            case 253:
                imgView = n16;
                break;
            case 254:
                imgView = o16;
                break;
            case 255:
                imgView = p16;
                break;

        }


        return imgView;


    }

    private void onSelected(int i) {
        final ImageView iv = getSquareImageView(i);

        //TODO: this may be redundant
        currentPosition = i;
        selectedUnit = gamesetList.get(i);

        iv.setBackgroundColor(Color.parseColor("#00FF00"));

        for (int p = 0; p < 256; p++) {
            final ImageView space = getSquareImageView(p);
            // if statement for movement 1 in each direction
            if ((p == downOne(currentPosition)) || (p == upOne(currentPosition)) || (p == leftOne(currentPosition)) || (p == rightOne(currentPosition))) {

                //TODO: do i really need to do this?
                final int localP = p;

                space.setImageResource(R.drawable.free_indicator);
                space.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //TODO: on click of selectable squares
                        moveTo(localP);

                    }
                });


            }

            // for Deselect
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (playerColor.equals("red")) {
                        iv.setBackgroundColor(getBackgroundColor(-1));
                    } else if (playerColor.equals("blue")) {
                        iv.setBackgroundColor(getBackgroundColor(-2));
                    }

                    getSquareImageView(currentPosition).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            onSelected(currentPosition);
                        }
                    });

                    for (int p = 0; p < 256; p++) {
                        final ImageView space = getSquareImageView(p);
                        // if statement for +1 forward
                        if ((p == currentPosition + 16) || (p == currentPosition - 16) || (p == currentPosition + 1) || (p == currentPosition - 1)) {

                            space.setImageResource(R.drawable.free_square);
                            space.setOnClickListener(null);


                        }
                    }


                }
            });


        }
    }

    private void moveTo(int moveTo) {

        Log.e("moveTo selectedUnit", " " + selectedUnit);
        Log.e("moveTo currentPosition", " " + currentPosition);
        Log.e("moveTo moveTo", " " + moveTo);

        ImageView space = getSquareImageView(moveTo);
        ImageView fromView = getSquareImageView(currentPosition);


        // draw piece in new space
        // update boardsetList and gamesetList
        if (turn.equals("red")) {
            if (unitEquiptment.equals("none")) {
                space.setImageResource(R.drawable.red_none);

                // get prior color to send to fillSpiller
                int priorColor = Integer.valueOf(boardsetList.get(moveTo));

                // set in boardsetList, -2 for blue and -1 for red
                boardsetList.set(moveTo, "-1");
                boardsetList.set(currentPosition, "-1");

                turn = "blue";
                // send moveTo position and the color it was previously to fillSpiller method
                fillSpiller(moveTo, priorColor, 1);



            }
            space.setBackgroundColor(Color.parseColor("#FF0000"));
            fromView.setImageResource(R.drawable.free_square);


        } else if (turn.equals("blue")) {
            if (unitEquiptment.equals("none")) {
                space.setImageResource(R.drawable.blue_none);

                // get prior color to send to fillSpiller
                int priorColor = Integer.valueOf(boardsetList.get(moveTo));

                // set in boardsetList -2 for blue, -1 for red
                boardsetList.set(moveTo, "-2");
                boardsetList.set(currentPosition, "-2");


                turn = "red";
                // send moveTo position and the color it was previously to fillSpiller method
                fillSpiller(moveTo, priorColor, 2);


            }
            space.setBackgroundColor(Color.parseColor("#0000FF"));
            fromView.setImageResource(R.drawable.free_square);

        }


        // set in gamesetList
        gamesetList.set(moveTo, selectedUnit);
        if (currentPosition != 999) {
            gamesetList.set(currentPosition, "free_square");
            currentPosition = 999;
        }


        // Create new gameset String
        gamesetString = "";
        for (int i = 0; i < gamesetList.size(); i++) {
            if (i != 0) {
                gamesetString = gamesetString + "," + gamesetList.get(i);
            }
            if (i == 0) {
                gamesetString = gamesetList.get(i);
            }
        }

        // Create new boardset String
        boardsetString = "";
        for (int i = 0; i < boardsetList.size(); i++) {
            if (i != 0) {
                boardsetString = boardsetString + "," + boardsetList.get(i);
            }
            if (i == 0) {
                boardsetString = boardsetList.get(i);
            }
        }

        // Clear selected which itself resets board
        clearSelected();

    }

    private void clearSelected() {
        selectedUnit = "";
        currentPosition = 999;

        // null onclick listeners
        for (int i = 0; i < 256; i++) {
            //If not a game piece and your turn, setOnclick null

            ImageView iView = getSquareImageView(i);
            iView.setOnClickListener(null);
        }

        // Reset board
        setBoard();
    }

    private int upOne(int here) {
        int up = 0;

        switch (here) {
            case 0:
                up = 240;
                break;
            case 1:
                up = 241;
                break;
            case 2:
                up = 242;
                break;
            case 3:
                up = 243;
                break;
            case 4:
                up = 244;
                break;
            case 5:
                up = 245;
                break;
            case 6:
                up = 246;
                break;
            case 7:
                up = 247;
                break;
            case 8:
                up = 248;
                break;
            case 9:
                up = 249;
                break;
            case 10:
                up = 250;
                break;
            case 11:
                up = 251;
                break;
            case 12:
                up = 252;
                break;
            case 13:
                up = 253;
                break;
            case 14:
                up = 254;
                break;
            case 15:
                up = 255;
                break;
            default:
                up = here - 16;
                break;

        }

        return up;
    }

    private int downOne(int here) {
        int down = 0;

        switch (here) {
            case 240:
                down = 0;
                break;
            case 241:
                down = 1;
                break;
            case 242:
                down = 2;
                break;
            case 243:
                down = 3;
                break;
            case 244:
                down = 4;
                break;
            case 245:
                down = 5;
                break;
            case 246:
                down = 6;
                break;
            case 247:
                down = 7;
                break;
            case 248:
                down = 8;
                break;
            case 249:
                down = 9;
                break;
            case 250:
                down = 0;
                break;
            case 251:
                down = 11;
                break;
            case 252:
                down = 12;
                break;
            case 253:
                down = 13;
                break;
            case 254:
                down = 14;
                break;
            case 255:
                down = 15;
                break;
            default:
                down = here + 16;
                break;

        }

        return down;
    }

    private int leftOne(int here) {
        int left = 0;

        switch (here) {
            case 0:
                left = 15;
                break;
            case 16:
                left = 31;
                break;
            case 32:
                left = 47;
                break;
            case 48:
                left = 63;
                break;
            case 64:
                left = 79;
                break;
            case 80:
                left = 95;
                break;
            case 96:
                left = 111;
                break;
            case 112:
                left = 127;
                break;
            case 128:
                left = 143;
                break;
            case 144:
                left = 159;
                break;
            case 160:
                left = 175;
                break;
            case 176:
                left = 191;
                break;
            case 192:
                left = 207;
                break;
            case 208:
                left = 223;
                break;
            case 224:
                left = 239;
                break;
            case 240:
                left = 255;
                break;
            default:
                left = here - 1;
                break;

        }

        return left;
    }

    private int rightOne(int here) {
        int right = 0;

        switch (here) {
            case 15:
                right = 0;
                break;
            case 31:
                right = 16;
                break;
            case 47:
                right = 32;
                break;
            case 63:
                right = 48;
                break;
            case 79:
                right = 64;
                break;
            case 95:
                right = 80;
                break;
            case 111:
                right = 96;
                break;
            case 127:
                right = 112;
                break;
            case 143:
                right = 128;
                break;
            case 159:
                right = 144;
                break;
            case 175:
                right = 160;
                break;
            case 191:
                right = 176;
                break;
            case 207:
                right = 192;
                break;
            case 223:
                right = 208;
                break;
            case 239:
                right = 224;
                break;
            case 255:
                right = 240;
                break;
            default:
                right = here + 1;
                break;

        }

        return right;
    }

    //TODO: Fix StackOverflowError
    private void fillSpiller(int square, int color, int red_or_blue) {
        // Set default setColor to black to make bugs obvious
        String setColor = "0";

        if (red_or_blue == 1) {
            setColor = "-1";
        } else if (red_or_blue == 2) {
            setColor = "-2";
        }

        if (Integer.valueOf(boardsetList.get(upOne(square))) == color) {
            boardsetList.set(upOne(square), setColor);
            fillSpiller(upOne(square), color, red_or_blue);
        }
        if (Integer.valueOf(boardsetList.get(downOne(square))) == color) {
            boardsetList.set(downOne(square), setColor);
            fillSpiller(downOne(square), color, red_or_blue);
        }
        if (Integer.valueOf(boardsetList.get(leftOne(square))) == color) {
            boardsetList.set(leftOne(square), setColor);
            fillSpiller(leftOne(square), color, red_or_blue);
        }
        if (Integer.valueOf(boardsetList.get(rightOne(square))) == color) {
            boardsetList.set(rightOne(square), setColor);
            fillSpiller(rightOne(square), color, red_or_blue);
        }
    }

    private void checkWin() {
        TextView displayResult = (TextView) findViewById(R.id.display_result);

        gamesetList = Arrays.asList(gamesetString.split("\\s*,\\s*"));

        if (playerColor.equals("red")) {
            for (int i = 0; i < gamesetList.size(); i++) {
                if (gamesetList.get(i).equals("red_none") && (boardsetList.get(i).equals("-2"))) {
                    displayResult.setText("You Lose!");
                    displayResult.setBackgroundColor(Color.parseColor("#6EECC485"));
                    displayResult.setVisibility(View.VISIBLE);

                }
                if (gamesetList.get(i).equals("blue_none") && (boardsetList.get(i).equals("-1"))) {
                    displayResult.setText("You Win!");
                    displayResult.setBackgroundColor(Color.parseColor("#6Ea1e9a1"));
                    displayResult.setVisibility(View.VISIBLE);

                }
            }

        }
        if (playerColor.equals("blue")) {
            for (int i = 0; i < gamesetList.size(); i++) {
                if (gamesetList.get(i).equals("blue_none") && (boardsetList.get(i).equals("-1"))) {
                    displayResult.setText("You Lose!");
                    displayResult.setBackgroundColor(Color.parseColor("#6EECC485"));
                    displayResult.setVisibility(View.VISIBLE);

                }
                if (gamesetList.get(i).equals("red_none") && (boardsetList.get(i).equals("-2"))) {
                    displayResult.setText("You Win!");
                    displayResult.setBackgroundColor(Color.parseColor("#6Ea1e9a1"));
                    displayResult.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

    }

    @Override
    public void onPause() {
        super.onPause();

        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
