package land.generic.kdorf.modules.zalgo

import com.soywiz.korim.font.CharacterSet
import java.nio.ByteBuffer
import javax.inject.Inject
import kotlin.random.Random

class ZalgoTransformer @Inject constructor(val chaos: Random) {
    private fun generateCruft(charset: List<Char>, countRange: IntRange) =
        (0 until countRange.random(chaos))
            .map { charset.random(chaos) }
            .joinToString("")

    fun generateCruft(ups: IntRange, mids: IntRange, downs: IntRange) =
        generateCruft(CHAR_UP, ups) + generateCruft(CHAR_MID, mids) + generateCruft(CHAR_DOWN, downs)

    fun transform(input: String, ups: IntRange, mids: IntRange, downs: IntRange) =
        input
            .map { it + generateCruft(ups, mids, downs) }
            .joinToString("")


}


val CHAR_UP = listOf(
    '\u030D', '\u030E', '\u0304', '\u0305', '\u033F',
    '\u0311', '\u0306', '\u0310', '\u0352', '\u0357',
    '\u0351', '\u0307', '\u0308', '\u030A', '\u0342',
    '\u0343', '\u0344', '\u034A', '\u034B', '\u034C',
    '\u0303', '\u0302', '\u030C', '\u0350', '\u0300',
    '\u0301', '\u030B', '\u030F', '\u0312', '\u0313',
    '\u0314', '\u033D', '\u0309', '\u0363', '\u0364',
    '\u0365', '\u0366', '\u0367', '\u0368', '\u0369',
    '\u036A', '\u036B', '\u036C', '\u036D', '\u036E',
    '\u036F', '\u033E', '\u035B', '\u0346', '\u031A'
)

val CHAR_MID = listOf(
    '\u0315', '\u031B', '\u0340', '\u0341', '\u0358',
    '\u0321', '\u0322', '\u0327', '\u0328', '\u0334',
    '\u0335', '\u0336', '\u034F', '\u035C', '\u035D',
    '\u035E', '\u035F', '\u0360', '\u0362', '\u0338',
    '\u0337', '\u0361', '\u0489'
)

val CHAR_DOWN = listOf(
    '\u0316', '\u0317', '\u0318', '\u0319', '\u031C',
    '\u031D', '\u031E', '\u031F', '\u0320', '\u0324',
    '\u0325', '\u0326', '\u0329', '\u032A', '\u032B',
    '\u032C', '\u032D', '\u032E', '\u032F', '\u0330',
    '\u0331', '\u0332', '\u0333', '\u0339', '\u033A',
    '\u033B', '\u033C', '\u0345', '\u0347', '\u0348',
    '\u0349', '\u034D', '\u034E', '\u0353', '\u0354',
    '\u0355', '\u0356', '\u0359', '\u035A', '\u0323'
)

val CHAR_SIMPLE = listOf(
    '\u0300',
    '\u0301', '\u0302', '\u0303', '\u0304', '\u0305', '\u0306', '\u0307', '\u0308', '\u0309', '\u0310',
    '\u0311', '\u0312', '\u0313', '\u0314', '\u0315', '\u0316', '\u0317', '\u0318', '\u0319', '\u0320',
    '\u0321', '\u0322', '\u0323', '\u0324', '\u0325', '\u0326', '\u0327', '\u0328', '\u0329', '\u0330',
    '\u0331', '\u0332', '\u0333', '\u0334', '\u0335', '\u0336', '\u0337', '\u0338', '\u0339', '\u0340',
    '\u0341', '\u0342', '\u0343', '\u0344', '\u0345', '\u0346', '\u0347', '\u0348', '\u0349', '\u0350',
    '\u0351', '\u0352', '\u0353', '\u0354', '\u0355', '\u0356', '\u0357', '\u0358', '\u0359', '\u0360',
    '\u036A', '\u036B', '\u036C', '\u036D', '\u036E', '\u036F'
)

val ZalgoCharacterSet = CharacterSet(
    CharacterSet.LATIN_ALL.codePoints +
            CHAR_SIMPLE.map { it.code }.toIntArray() +
            CHAR_DOWN.map { it.code }.toIntArray() +
            CHAR_UP.map { it.code }.toIntArray() +
            CHAR_MID.map { it.code }.toIntArray()
)