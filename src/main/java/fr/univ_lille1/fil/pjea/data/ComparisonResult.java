package fr.univ_lille1.fil.pjea.data;

import java.io.File;

import fr.univ_lille1.fil.pjea.comparators.FileComparator;

/**
 * Contient le résultat de comparaison entre deux fichier.<br>
 * Ce résultat est produit par un {@link FileComparator}
 */
public class ComparisonResult {
	
	/**
	 * Indique si les fichiers comparés sont, selon l'algorithme,
	 * plagiés entre eux, ou non. Une valeur <code>null</code> indique
	 * que l'algorithme ne peut certifier ni l'un ni l'autre.
	 */
	public final Boolean isPlagiarism; // plagiat (fr) = plagiarism (en)
	
	/**
	 * Indique le taux de ressemblance entre les deux fichiers.
	 * 0 indique le moins de ressemblance, et 1 indique le plus de
	 * ressemblance. Il est possible d'avoir des valeurs dépassant l'intervalle
	 * <code>[0; 1]</code>, mais elle risque d'être ramenée à une valeur valide
	 * pour l'affichage.
	 */
	public final double similarityRate;
	
	/**
	 * Construit un simple résultat de comparaison.
	 * @param plagiarism <code>true</code> si l'algorithme est sûr de la présence d'un plagiat.
	 * <code>false</code> si l'algorithme est sûr qu'il n'y a pas de plagiat.
	 * <code>null</code> si il n'est sûr de rien.
	 * @param similarity le taux de similarité entre 0 (différent) et 1 (identique) entre les deux fichiers.
	 * La valeur peut sortir de l'intervalle <code>[0; 1]</code> bien que l'affichage final corrigera la valeur.
	 */
	public ComparisonResult(Boolean plagiarism, double similarity) {
		isPlagiarism = plagiarism;
		similarityRate = similarity;
	}
	
	
	/**
	 * Construit un résultat de comparaison basé sur d'autres résultats.<br>
	 * <br>
	 * Seul l'indicateur de plagiat {@link #isPlagiarism} est calculée par cette méthode, déduite par
	 * les indicateurs présents dans chaque résultats passés en paramètre. Si au moins un des indicateurs a
	 * pour valeur <code>true</code>, la valeur finale sera <code>true</code>. Sinon, si au moins un des
	 * indicateurs a pour valeur <code>false</code>, alors la valeur de <code>isPlagiarism</code> sera
	 * <code>false</code>. Dans les autres cas, la valeur sera <code>null</code>.<br>
	 * <br>
	 * Le taux de similarité n'est pas calculé et par conséquent doit être passé via le paramètre
	 * <code>mergedSimilarity</code>.
	 * @param mergedSimilarity score de similarité, déjà calculé
	 * @param results les résultats de comparaison provenants d'autres algorithmes.
	 */
	public ComparisonResult(double mergedSimilarity, ComparisonResult... results) {
		similarityRate = mergedSimilarity;
		boolean containsFalse = false;
		for (ComparisonResult result : results) {
			if (result == null) continue;
			if (Boolean.TRUE.equals(result.isPlagiarism)) {
				isPlagiarism = true;
				return;
			}
			if (Boolean.FALSE.equals(result.isPlagiarism)) {
				containsFalse = true;
			}
		}
		isPlagiarism = containsFalse ? false : null;
	}
	
	
	
	
	
	/**
	 * Tronque la valeur v entre min et max.
	 * @param v la valeur à tronquer
	 * @param min la valeur minimum à retourner
	 * @param max la valeur maximum à retourner
	 * @return max si v &gt; max, min si v &lt; min, v sinon.
	 * Si max &lt; min, alors min sera retournée.
	 */
	
	public static double truncateToRange(double v, double min, double max) {
		return Math.max(min, Math.min(max, v));
	}
	
	
	
	
	/**
	 * Affiche sur la sortie standard le résultat de comparaison entre les deux fichiers
	 * @param file1 le premier fichier comparé
	 * @param file2 le deuxième fichier avec lequel le 1<sup>er</sup> est comparé
	 * @param result le résultat de comparaison fourni par l'algorithme
	 */
	public static void output(File file1, File file2, ComparisonResult result) {
		int porcent = (int) (Math.round(((result != null) ? result.similarityRate : 0) * 100));
		System.out.println(file1 + " and " + file2 + ": " + ((porcent > 100) ? 100 : (porcent < 0) ? 0 : porcent) + "% "
				+ ((result == null || result.isPlagiarism == null) ? 'P' : (result.isPlagiarism ? 'O' : 'N')));
		
	}
	
	
}
